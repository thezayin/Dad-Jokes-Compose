package com.thezayin.dadjokes.core.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.sdp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                modifier = Modifier.size(20.sdp),
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = colorResource(R.color.black),
                contentDescription = null
            )
        }
    }
}