package com.yoonit.handshakedemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var handshake: Handshake

    private val handshakeListener = object : HandshakeListener {
        override fun continueExecution() {
            Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG).show()
        }
        override fun handleFailedUpdate(type: String, result: String) {
            Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.handshake = Handshake(applicationContext, handshakeListener)

        update_fingerprint.setOnClickListener {
            handshake.updateFingerprint(BuildConfig.PUBLIC_KEY, BuildConfig.URL)
        }
    }
}