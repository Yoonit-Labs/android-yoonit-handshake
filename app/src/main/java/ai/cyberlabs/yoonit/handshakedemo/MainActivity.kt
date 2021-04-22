package ai.cyberlabs.yoonit.handshakedemo

import ai.cyberlabs.yoonit.yoonit.handshake.Handshake
import ai.cyberlabs.yoonit.yoonit.handshake.HandshakeListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val handshakeListener: HandshakeListener = object : HandshakeListener {
        override fun continueExecution() {
            Toast.makeText(applicationContext, "SUCCESS", Toast.LENGTH_LONG).show()
        }

        override fun handleFailedUpdate(type: String, isPerformingUpdate: Boolean, result: String) {
            Toast.makeText(applicationContext, result, Toast.LENGTH_LONG).show()
        }

    }

    private val handshake: Handshake = Handshake(applicationContext,
        "BEcTK0YpgYom5ifxUyX5yKDNUBaaLQpWxU361n+ihb5EAnKvABWde1P6lnZR3CR9ykrTLrO0ANdivFabS20iGOA=",
        "https://y9ggj3nnv8.execute-api.us-east-1.amazonaws.com/v1/sslpinning",
        handshakeListener
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        update_fingerprint.setOnClickListener { handshake.updateFingerptints() }
    }
}