package com.thezayin.start_up.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.common.BannerAd
import com.thezayin.dadjokes.core.common.TopBar
import com.thezayin.dadjokes.screens.setting.component.SettingOptionsList

/**
 * Composable function that defines the content layout for the settings screen.
 *
 * @param onBackClick Callback function to handle the back button click action.
 */
@Composable
fun SettingScreenContent(
    analytics: Analytics,
    showBannerAd: Boolean,
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            TopBar(
                modifier = Modifier,
                onBackClick = {
                    onBackClick()
                },
            )
        },
        bottomBar = {
            BannerAd(showBannerAd)
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SettingOptionsList(analytics = analytics)
        }
    }
}
