package com.thezayin.dadjokes.screens.saved.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.framework.extension.functions.copyText
import com.thezayin.dadjokes.core.framework.extension.functions.share
import ir.kaaveh.sdpcompose.sdp

@Composable
fun PreviewBottomButton(
    joke: String?,
    deleteJoke: () -> Unit,
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.sdp),
        horizontalArrangement = Arrangement.spacedBy(
            space = 10.sdp, alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { context.copyText(joke ?: "") }) {
            Icon(
                imageVector = Icons.Outlined.CopyAll,
                tint = colorResource(id = R.color.black),
                contentDescription = null,
                modifier = Modifier.size(24.sdp)
            )
        }
        IconButton(
            onClick = {
                deleteJoke()
            }) {
            Icon(
                modifier = Modifier.size(24.sdp),
                imageVector = Icons.Outlined.Delete,
                tint = colorResource(id = R.color.red),
                contentDescription = null
            )
        }

        IconButton(
            onClick = {
                context.share(joke ?: "")
            }) {
            Icon(
                imageVector = Icons.Outlined.Share,
                tint = colorResource(id = R.color.black),
                contentDescription = null,
                modifier = Modifier.size(24.sdp)
            )
        }
    }
}