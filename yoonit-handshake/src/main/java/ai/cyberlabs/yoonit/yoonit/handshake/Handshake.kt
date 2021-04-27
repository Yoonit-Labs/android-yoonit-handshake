package ai.cyberlabs.yoonit.yoonit.handshake

import android.content.Context
import android.util.Base64
import com.wultra.android.sslpinning.*
import com.wultra.android.sslpinning.integration.DefaultUpdateObserver
import com.wultra.android.sslpinning.integration.powerauth.powerAuthCertStore
import java.net.URL

class Handshake(context: Context) {

    private val myContext = context

    fun updateFingerprint(publicKey: String, serviceUrl: String, result: (String) -> Unit) {
        val publicKeyByteArray: ByteArray = Base64.decode(publicKey, Base64.NO_WRAP)

        val configuration = CertStoreConfiguration.Builder(
                serviceUrl = URL(serviceUrl),
                publicKey = publicKeyByteArray)
                .build()

        val certStore: CertStore = CertStore.powerAuthCertStore(configuration, myContext)

        certStore.update(UpdateMode.DEFAULT, object : DefaultUpdateObserver() {
            override fun continueExecution() {
                // Certstore is likely up-to-date, you can resume execution of your code.
                result(UpdateResult.OK.name)
            }

            override fun handleFailedUpdate(type: UpdateType, result: UpdateResult) {
                // There was an error during the update, present an error to the user.
                result(result.name)
            }
        })
    }
}