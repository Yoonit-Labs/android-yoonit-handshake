package ai.cyberlabs.yoonit.yoonit.handshake

import com.wultra.android.sslpinning.UpdateResult
import com.wultra.android.sslpinning.UpdateType

interface HandshakeListener {

    fun continueExecution()

    fun handleFailedUpdate(type: String, isPerformingUpdate: Boolean, result: String)
}