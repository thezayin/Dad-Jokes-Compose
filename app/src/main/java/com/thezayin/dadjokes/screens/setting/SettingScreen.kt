package com.thezayin.dadjokes.screens.setting

import androidx.compose.runtime.Composable
import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent
import com.thezayin.dadjokes.core.common.GlassComponent
import com.thezayin.start_up.setting.component.SettingScreenContent
import org.koin.compose.koinInject

/**
 * Composable function for displaying the settings screen.
 *
 * @param onBackClick Callback function to handle the back button click action.
 */
@Composable
fun SettingScreen(
    onBackClick: () -> Unit,
    vm: SettingViewModel = koinInject()
) {
    vm.analytics.logEvent(AnalyticsEvent.ScreenViewEvent("SettingsScreenView"))

    GlassComponent()

    SettingScreenContent(
        analytics = vm.analytics,
        showBannerAd = vm.remoteConfig.adBannerSetting,
        onBackClick = onBackClick
    )
}
