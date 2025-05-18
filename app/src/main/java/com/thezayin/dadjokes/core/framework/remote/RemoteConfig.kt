package com.thezayin.dadjokes.core.framework.remote

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import timber.log.Timber

private const val AD_ON_RESUME = "ad_on_resume"
private const val AD_BANNER_SPLASH = "ad_banner_splash"
private const val AD_ON_SPLASH = "ad_on_splash"
private const val AD_ON_ONBOARDING_COMPLETED = "ad_on_onboarding_completed"
private const val AD_BANNER_ONBOARDING = "ad_on_onboarding"
private const val AD_BANNER_SETTING = "ad_banner_setting"
private const val AD_BANNER_HOME = "ad_banner_home"
private const val AD_INTERSTITIAL_HOME_AI = "ad_interstitial_home_ai"
private const val AD_REWARDED_HOME_LIKED = "ad_interstitial_home_liked"
private const val AD_INTERSTITIAL_HOME_NEXT = "ad_interstitial_home_next"
private const val AD_REWARDED_AI_GENERATE = "ad_rewarded_ai_generate"
private const val AD_REWARDED_AI_BANNER = "ad_rewarded_ai_banner"
private const val AD_PREVIEW_JOKE_DELETE = "ad_preview_joke_delete"
private const val AD_BANNER_PREVIEW = "ad_banner_preview"
private const val AD_SAVE_JOKE_CLICK = "ad_save_joke_click"
private const val AD_SAVE_BANNER = "ad_save_banner"
private const val AD_SAVE_DELETE_ALL = "ad_save_delete_all"
private const val BANNER_AD_ID = "banner_ad_id"
private const val INTERSTITIAL_AD_ID = "interstitial_ad_id"
private const val REWARDED_AD_ID = "rewarded_ad_id"
private const val AD_ON_RESUME_ID = "ad_on_resume_id"

@Suppress("DEPRECATION")
class RemoteConfig(
) {
    private val default: Map<String, Any> = mapOf(
        AD_ON_RESUME to true,
        AD_BANNER_SPLASH to true,
        AD_ON_SPLASH to true,
        AD_ON_ONBOARDING_COMPLETED to true,
        AD_BANNER_ONBOARDING to true,
        AD_BANNER_SETTING to true,
        AD_BANNER_HOME to true,
        AD_INTERSTITIAL_HOME_AI to true,
        AD_REWARDED_HOME_LIKED to true,
        AD_INTERSTITIAL_HOME_NEXT to true,
        AD_REWARDED_AI_GENERATE to true,
        AD_REWARDED_AI_BANNER to true,
        AD_PREVIEW_JOKE_DELETE to true,
        AD_BANNER_PREVIEW to true,
        AD_SAVE_JOKE_CLICK to true,
        AD_SAVE_BANNER to true,
        AD_SAVE_DELETE_ALL to true,
        BANNER_AD_ID to "ca-app-pub-3940256099942544/6300978111",
        INTERSTITIAL_AD_ID to "ca-app-pub-3940256099942544/1033173712",
        REWARDED_AD_ID to "ca-app-pub-3940256099942544/5224354917",
        AD_ON_RESUME_ID to "ca-app-pub-3940256099942544/9257395921",
    )

    private val config = FirebaseRemoteConfig.getInstance().apply {
        setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            })
        setDefaultsAsync(default)
        fetchAndActivate().addOnCompleteListener {
            Timber.tag("RemoteConfig")
                .d("fetchAndActivate: ${all.mapValues { (_, v) -> v.asString() }}")
        }
    }

    val adOnResume: Boolean
        get() = config[AD_ON_RESUME].asBoolean()

    val adBannerSplash: Boolean
        get() = config[AD_BANNER_SPLASH].asBoolean()

    val adOnSplash: Boolean
        get() = config[AD_ON_SPLASH].asBoolean()

    val adOnOnboardingCompleted: Boolean
        get() = config[AD_ON_ONBOARDING_COMPLETED].asBoolean()

    val adBannerOnboarding: Boolean
        get() = config[AD_BANNER_ONBOARDING].asBoolean()

    val adBannerSetting: Boolean
        get() = config[AD_BANNER_SETTING].asBoolean()

    val adBannerHome: Boolean
        get() = config[AD_BANNER_HOME].asBoolean()

    val adInterstitialHomeAi: Boolean
        get() = config[AD_INTERSTITIAL_HOME_AI].asBoolean()

    val adRewardedHomeLiked: Boolean
        get() = config[AD_REWARDED_HOME_LIKED].asBoolean()

    val adInterstitialHomeNext: Boolean
        get() = config[AD_INTERSTITIAL_HOME_NEXT].asBoolean()

    val adRewardedAiGenerate: Boolean
        get() = config[AD_REWARDED_AI_GENERATE].asBoolean()

    val adRewardedAiBanner: Boolean
        get() = config[AD_REWARDED_AI_BANNER].asBoolean()

    val adPreviewJokeDelete: Boolean
        get() = config[AD_PREVIEW_JOKE_DELETE].asBoolean()

    val adBannerPreview: Boolean
        get() = config[AD_BANNER_PREVIEW].asBoolean()

    val adSaveJokeClick: Boolean
        get() = config[AD_SAVE_JOKE_CLICK].asBoolean()

    val adSaveBanner: Boolean
        get() = config[AD_SAVE_BANNER].asBoolean()

    val adSaveDeleteAll: Boolean
        get() = config[AD_SAVE_DELETE_ALL].asBoolean()

    val bannerAdId: String
        get() = config[BANNER_AD_ID].asString()

    val adOnResumeId: String
        get() = config[AD_ON_RESUME_ID].asString()

    val interstitialAdId: String
        get() = config[INTERSTITIAL_AD_ID].asString()

    val rewardedAdId: String
        get() = config[REWARDED_AD_ID].asString()
}
