package ai.cyberlabs.yoonit.yoonit.handshake

import android.content.Context
import android.util.Base64
import android.util.Log
import com.wultra.android.sslpinning.*
import com.wultra.android.sslpinning.integration.DefaultUpdateObserver
import com.wultra.android.sslpinning.integration.powerauth.powerAuthCertStore
import java.net.URL


class Handshake(context: Context,
                publicKey: String,
                serviceUrl: String,
                handshakeListener: HandshakeListener) {

    private val myContext = context

    private val listener = handshakeListener

    private val publicKeyByteArray: ByteArray = Base64.decode(publicKey, Base64.NO_WRAP)

    private val configuration = CertStoreConfiguration.Builder(
            serviceUrl = URL(serviceUrl),
            publicKey = publicKeyByteArray)
            .build()

    private val certStore: CertStore = CertStore.powerAuthCertStore(configuration, myContext)

    fun updateFingerptints() {
        certStore.update(UpdateMode.DEFAULT, object : DefaultUpdateObserver() {
            override fun continueExecution() {
                // Certstore is likely up-to-date, you can resume execution of your code.
                listener.continueExecution()
            }

            override fun handleFailedUpdate(type: UpdateType, result: UpdateResult) {
                // There was an error during the update, present an error to the user.
                if (result == UpdateResult.OK) {
                    listener.continueExecution()
                    return
                }

                listener.handleFailedUpdate(type.name, type.isPerformingUpdate, result.name)
            }
        })
    }
}