package com.thezayin.dadjokes.screens.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.dadjokes.R
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HomeTopBar(
    navigateToSettingScreen: () -> Unit, navigateToSavedJokesScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.sdp, vertical = 10.sdp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navigateToSettingScreen() },
        ) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                tint = colorResource(id = R.color.black),
                contentDescription = null,
                modifier = Modifier.size(24.sdp)
            )
        }

        IconButton(
            onClick = { navigateToSavedJokesScreen() },
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = colorResource(id = R.color.red),
                modifier = Modifier.size(24.sdp)
            )
        }
    }
}