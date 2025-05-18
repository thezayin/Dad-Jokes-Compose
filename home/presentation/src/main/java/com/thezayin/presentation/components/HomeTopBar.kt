package com.thezayin.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import R

@Composable
fun HomeTopBar(
    modifier: Modifier, navigateToSettingScreen: () -> Unit, navigateToSavedJokesScreen: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = R.drawable.ic_toolbar),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navigateToSettingScreen()
                })
        Image(painter = painterResource(id = R.drawable.ic_liked),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 20.dp)
                .size(25.dp)
                .clickable {
                    navigateToSavedJokesScreen()
                })
    }
}