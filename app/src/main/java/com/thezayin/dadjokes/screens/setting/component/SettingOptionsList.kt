package com.thezayin.dadjokes.screens.setting.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import ir.kaaveh.sdpcompose.sdp
import kotlinx.coroutines.delay

@Composable
fun SettingOptionsList(
    analytics: Analytics
) {
    val headerVisible = remember { mutableStateOf(false) }
    val otherListVisible = remember { mutableStateOf(false) }
    val legalListVisible = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(300)
        headerVisible.value = true
        delay(300)
        otherListVisible.value = true
        delay(300)
        legalListVisible.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.sdp)
    ) {
        AnimatedVisibility(
            visible = headerVisible.value,
            enter = slideInVertically(
                initialOffsetY = { it }, animationSpec = tween(
                    durationMillis = 1000, easing = LinearOutSlowInEasing
                )
            ),
        ) {
            SettingHeader()
        }

        AnimatedVisibility(
            visible = otherListVisible.value,
            enter = slideInVertically(
                initialOffsetY = { it }, animationSpec = tween(
                    durationMillis = 1000, easing = LinearOutSlowInEasing
                )
            ),
        ) {
            OtherListComponent(analytics)
        }

        AnimatedVisibility(
            visible = legalListVisible.value,
            enter = slideInVertically(
                initialOffsetY = { it }, animationSpec = tween(
                    durationMillis = 1000, easing = LinearOutSlowInEasing
                )
            ),
        ) {
            LegalListComponent(analytics)
        }
    }
}