package ai.cyberlabs.yoonit.yoonit.handshake

interface HandshakeListener {
    fun continueExecution()
    fun handleFailedUpdate(type: String, result: String)
}