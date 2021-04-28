package com.yoonit.handshakedemo

interface HandshakeListener {
    fun continueExecution()
    fun handleFailedUpdate(type: String, result: String)
}