package com.thezayin.dadjokes.core.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun ErrorDialog(errorMessage: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.sdp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = errorMessage.ifEmpty { "Check your internet connection and try again." },
            fontSize = 12.ssp,
            color = colorResource(id = R.color.black),
            textAlign = TextAlign.Center,
        )
    }
}