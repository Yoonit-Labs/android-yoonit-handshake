package ai.cyberlabs.yoonit.handshakedemo

import ai.cyberlabs.yoonit.yoonit.handshake.Handshake
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var handshake: Handshake

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.handshake = Handshake(applicationContext)

        update_fingerprint.setOnClickListener {
            handshake.updateFingerprint(BuildConfig.PUBLIC_KEY, BuildConfig.URL)
            { Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show() }
        }
    }
}