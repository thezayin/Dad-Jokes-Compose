package com.thezayin.setting

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.framework.remote.RemoteConfig
import kotlinx.coroutines.launch

/**
 * ViewModel for handling settings-related logic, including managing Google Ads and Remote Config.
 *
 * @param googleManager Handles interactions with Google Ads, such as loading native ads.
 * @param remoteConfig Manages remote configurations to dynamically control settings and features.
 */
class SettingViewModel(
    val googleManager: GoogleManager,
    val remoteConfig: RemoteConfig
) : ViewModel() {

    // Mutable state to hold the currently loaded Native Ad.
    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set  // Private setter to control access to the state

    /**
     * Fetches a new native ad and updates the nativeAd state.
     * If the ad fails to load, retries after a delay of 3 seconds.
     */
    fun getNativeAd() = viewModelScope.launch {
        googleManager.getNativeAd { ad ->
            nativeAd.value = ad
        }
    }
}