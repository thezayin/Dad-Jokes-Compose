package com.thezayin.dadjokes.screens.home.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun AiCard(
    onClick: () -> Unit = {},
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.background)
        ),
        onClick = onClick,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(24.sdp),
        modifier = Modifier
            .padding(horizontal = 25.sdp)
            .padding(top = 10.sdp)
            .fillMaxWidth()
            .height(60.sdp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_magic),
                tint = colorResource(id = R.color.black),
                contentDescription = null,
                modifier = Modifier
                    .size(14.sdp)
            )
            Spacer(modifier = Modifier.size(5.sdp))
            Text(
                text = "Create Your Own Joke with AI!",
                color = colorResource(id = R.color.black),
                fontSize = 12.ssp,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                tint = colorResource(id = R.color.black),
                contentDescription = null,
                modifier = Modifier
                    .size(20.sdp)
                    .padding(start = 5.sdp)
            )
        }
    }
}