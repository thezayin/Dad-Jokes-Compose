package com.thezayin.framework.remote

import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings


private const val SHOW_AD_ON_SAVE_SCREEN_SELECTION = "show_ad_on_save_screen_selection"
private const val SHOW_NATIVE_AD_ON_LOADING_DIALOG = "show_native_ad_on_loading_dialog"
private const val SHOW_NATIVE_AD_ON_SAVED_SCREEN = "show_native_ad_on_saved_screen"
private const val SHOW_AD_ON_SAVED_AD_CLICK = "show_ad_on_saved_ad_click"
private const val SHOW_NATIVE_AD_ON_HOME = "show_native_ad_on_home"
private const val SHOW_AD_ON_APP_OPEN = "show_ad_on_app_open"
private const val INIT_ADS = "init_ads"

@Suppress("DEPRECATION")
class RemoteConfig(
) {
    private val default: Map<String, Any> = mapOf(
        SHOW_AD_ON_SAVE_SCREEN_SELECTION to false,
        SHOW_NATIVE_AD_ON_LOADING_DIALOG to false,
        SHOW_NATIVE_AD_ON_SAVED_SCREEN to false,
        SHOW_AD_ON_SAVED_AD_CLICK to false,
        SHOW_NATIVE_AD_ON_HOME to false,
        SHOW_AD_ON_APP_OPEN to false,
        INIT_ADS to false
    )
    private val config = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        })
        setDefaultsAsync(default)
        fetchAndActivate().addOnCompleteListener {
            Log.d("RemoteConfig", "fetchAndActivate: ${all.mapValues { (k, v) -> v.asString() }}")
        }
    }

    val initAds: Boolean
        get() = config[INIT_ADS].asBoolean()

    val showAdOnSaveScreenSelection: Boolean
        get() = config[SHOW_AD_ON_SAVE_SCREEN_SELECTION].asBoolean()

    val showNativeAdOnLoadingDialog: Boolean
        get() = config[SHOW_NATIVE_AD_ON_LOADING_DIALOG].asBoolean()

    val showNativeAdOnSavedScreen: Boolean
        get() = config[SHOW_NATIVE_AD_ON_SAVED_SCREEN].asBoolean()

    val showAdOnSavedAdClick: Boolean
        get() = config[SHOW_AD_ON_SAVED_AD_CLICK].asBoolean()

    val showNativeAdOnHome: Boolean
        get() = config[SHOW_NATIVE_AD_ON_HOME].asBoolean()

    val showAdOnAppOpen: Boolean
        get() = config[SHOW_AD_ON_APP_OPEN].asBoolean()
}
