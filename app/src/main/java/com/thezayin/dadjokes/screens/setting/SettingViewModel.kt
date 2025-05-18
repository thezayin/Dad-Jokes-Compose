package com.thezayin.dadjokes.screens.setting

import androidx.lifecycle.ViewModel
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.framework.remote.RemoteConfig

/**
 * ViewModel for handling settings-related logic, including managing Google Ads and Remote Config.
 *
 * @param remoteConfig Manages remote configurations to dynamically control settings and features.
 */
class SettingViewModel(
    val remoteConfig: RemoteConfig,
    val analytics: Analytics
) : ViewModel()