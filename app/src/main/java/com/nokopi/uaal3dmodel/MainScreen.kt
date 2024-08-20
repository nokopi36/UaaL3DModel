package com.nokopi.uaal3dmodel

import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.unity3d.player.UnityPlayer

@Composable
fun MainScreen(
    unityPlayer: UnityPlayer,
    modifier: Modifier = Modifier,
) {
    val (inputText, setInputText) = remember {
        mutableStateOf("")
    }

    // Box(
    //     modifier = modifier.fillMaxWidth(),
    //     contentAlignment = Alignment.BottomCenter,
    // ) {
    //     AndroidView(
    //         modifier = Modifier
    //             .fillMaxSize(),
    //         factory = { context ->
    //             val view = LayoutInflater.from(context).inflate(R.layout.activity_main, null, false)
    //             view.apply {
    //                 val unityContainer: FrameLayout = findViewById(R.id.unity)
    //                 unityContainer.addView(unityPlayer)
    //             }
    //         }
    //     )
    //     Column(
    //         verticalArrangement = Arrangement.Center,
    //         horizontalAlignment = Alignment.CenterHorizontally,
    //     ) {
    //         Button(
    //             onClick = {
    //                 UnityPlayer.UnitySendMessage("PlayerController", "ChangePose", "")
    //             }
    //         ) {
    //             Text(text = "Pose Change")
    //         }
    //         TextField(
    //             value = inputText,
    //             onValueChange = { setInputText(it) }
    //         )
    //         Button(
    //             onClick = {
    //                 UnityPlayer.UnitySendMessage("Canvas", "TestText", inputText)
    //             }
    //         ) {
    //             Text(text = "送信")
    //         }
    //     }
    // }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            val unityViewHeight = screenHeight * 0.8f // 画面の80%の高さ
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(unityViewHeight)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            UnityPlayer.UnitySendMessage("Canvas", "TestText", "${dragAmount.x},${dragAmount.y}")
                            UnityPlayer.UnitySendMessage("unitychan", "ObjectRotator", "${dragAmount.y},${-dragAmount.x}")
                        }
                    },
                factory = { context ->
                    val view = LayoutInflater.from(context).inflate(R.layout.activity_main, null, false)
                    view.apply {
                        val unityContainer: FrameLayout = findViewById(R.id.unity)
                        unityContainer.addView(unityPlayer)
                    }
                }
            )
        }
        item {
            Button(
                onClick = {
                    UnityPlayer.UnitySendMessage("unitychan", "ChangePose", "")
                }
            ) {
                Text(text = "Pose Change")
            }
        }
        item {
            TextField(
                value = inputText,
                onValueChange = { setInputText(it) }
            )
        }
        item {
            Button(
                onClick = {
                    UnityPlayer.UnitySendMessage("Canvas", "TestText", inputText)
                }
            ) {
                Text(text = "送信")
            }
        }
        item {
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .background(Color.Red)
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    // モックのContextとLayoutInflaterを作成
    val context = LocalContext.current
    // UnityPlayerのモックを作成
    val unityPlayer = UnityPlayer(context) // プレビューでは空のモックを使う
    MainScreen(
        unityPlayer = unityPlayer,
    )
}