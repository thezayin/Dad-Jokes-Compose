package com.thezayin.dadjokes.screens.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun NextJokeButton(callback: () -> Unit) {
    Button(
        onClick = {
            callback()
        },
        modifier = Modifier
            .padding(vertical = 20.sdp)
            .width(100.sdp)
            .height(50.sdp),
        shape = RoundedCornerShape(24.sdp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.home_card_color),
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Next",
                color = colorResource(id = R.color.black),
                fontSize = 14.ssp,
                fontWeight = FontWeight.Bold,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                tint = colorResource(id = R.color.black),
                contentDescription = null,
                modifier = Modifier
                    .size(24.sdp)
            )
        }
    }
}