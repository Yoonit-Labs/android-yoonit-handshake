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

    private lateinit var handshake: Handshake

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.handshake = Handshake(applicationContext, handshakeListener)

        update_fingerprint.setOnClickListener { handshake.updateFingerprint(BuildConfig.PUBLIC_KEY, BuildConfig.URL,) }
    }
}