package com.nokopi.uaal3dmodel

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.ComposeView
import com.unity3d.player.UnityPlayer

class MainActivity : ComponentActivity() {
    private var unityPlayer: UnityPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        unityPlayer = UnityPlayer(this)
        setContentView(R.layout.activity_main)

        val frameLayout = findViewById<FrameLayout>(R.id.unity)
        frameLayout.addView(
            unityPlayer?.rootView,
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
        )

        val composeView = ComposeView(this).apply {
            setContent {
                MainScreen()
            }
        }

        val frameComposeLayout = findViewById<FrameLayout>(R.id.compose_view)
        frameComposeLayout.addView(
            composeView,
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
        )

        unityPlayer?.requestFocus()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        unityPlayer?.windowFocusChanged(hasFocus)
    }

    override fun onResume() {
        super.onResume()
        unityPlayer?.resume()
    }
}
