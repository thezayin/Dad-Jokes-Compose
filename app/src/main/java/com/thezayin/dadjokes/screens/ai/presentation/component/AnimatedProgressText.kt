package com.thezayin.dadjokes.screens.ai.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.ssp
import kotlinx.coroutines.delay

@Composable
fun AnimatedProgressText(text: String) {
    var dotCount by remember { mutableIntStateOf(1) }
    LaunchedEffect(text) {
        while (true) {
            delay(500L)
            dotCount = if (dotCount < 3) dotCount + 1 else 1
        }
    }

    Text(
        text = "$text${".".repeat(dotCount)}",
        fontSize = 10.ssp,
        color = colorResource(R.color.black),
        textAlign = TextAlign.Start
    )
}