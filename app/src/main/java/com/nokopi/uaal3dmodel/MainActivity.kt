package com.nokopi.uaal3dmodel

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.unity3d.player.UnityPlayer

class MainActivity : ComponentActivity() {
    private var unityPlayer: UnityPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        unityPlayer = UnityPlayer(this)
        val layoutInflater = LayoutInflater.from(this)

        setContent {
            MainScreen(
                unityPlayer = unityPlayer!!,
                layoutInflater = layoutInflater
            )
        }

        // setContentView(R.layout.activity_main)
        //
        // val frameLayout = findViewById<FrameLayout>(R.id.unity)
        // frameLayout.addView(
        //     unityPlayer?.rootView,
        //     FrameLayout.LayoutParams.MATCH_PARENT,
        //     FrameLayout.LayoutParams.MATCH_PARENT,
        // )
        //
        // val composeView = ComposeView(this).apply {
        //     setContent {
        //         MainScreen()
        //     }
        // }
        //
        // val frameComposeLayout = findViewById<FrameLayout>(R.id.compose_view)
        // frameComposeLayout.addView(
        //     composeView,
        //     FrameLayout.LayoutParams.MATCH_PARENT,
        //     FrameLayout.LayoutParams.MATCH_PARENT,
        // )

        unityPlayer?.requestFocus()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        unityPlayer?.windowFocusChanged(hasFocus)
    }

    override fun onDestroy() {
        super.onDestroy()
        unityPlayer?.destroy()
    }

    override fun onStop() {
        super.onStop()
        unityPlayer?.onStop()
    }

    override fun onStart() {
        super.onStart()
        unityPlayer?.onStart()
    }

    // Pause Unity
    override fun onPause() {
        super.onPause()
        unityPlayer?.onPause()
    }

    // Resume Unity
    override fun onResume() {
        super.onResume()
        unityPlayer?.onResume()
    }
}
