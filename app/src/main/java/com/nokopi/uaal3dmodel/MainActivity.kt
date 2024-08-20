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

        setContent {
            MainScreen(
                unityPlayer = unityPlayer!!,
            )
        }

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
