package com.nokopi.uaal3dmodel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.unity3d.player.UnityPlayer

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val (inputText, setInputText) = remember {
        mutableStateOf("")
    }
    Box(modifier = modifier.fillMaxSize()){
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    UnityPlayer.UnitySendMessage("PlayerController", "ChangePose", "")
                }
            ) {
                Text(text = "Pose Change")
            }
            TextField(
                value = inputText,
                onValueChange = { setInputText(it) }
            )
            Button(
                onClick = {
                    UnityPlayer.UnitySendMessage("Canvas", "TestText", inputText)
                }
            ) {
                Text(text = "送信")
            }
        }
    }

}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}