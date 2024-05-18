package com.thezayin.dadjokes.presentation.home.component

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
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.core.R

import com.thezayin.dadjokes.presentation.activity.component.PremiumButton
import com.thezayin.dadjokes.presentation.destinations.SavedJokesScreenDestination
import com.thezayin.dadjokes.presentation.destinations.SettingScreenDestination

@Composable
fun HomeTopBar(modifier: Modifier, navigator: DestinationsNavigator?) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp).padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_toolbar),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navigator?.navigate(SettingScreenDestination)
                }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_liked),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(25.dp)
                    .clickable {
                        navigator?.navigate(SavedJokesScreenDestination)
                    }
            )
            PremiumButton()
        }
    }
}