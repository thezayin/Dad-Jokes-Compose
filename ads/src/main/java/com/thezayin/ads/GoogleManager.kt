package com.thezayin.ads

import android.app.Activity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.thezayin.ads.builders.GoogleAppOpenAdLoader
import com.thezayin.ads.builders.GoogleInterstitialAdLoader
import com.thezayin.ads.builders.GoogleNativeAdLoader
import com.thezayin.ads.builders.GoogleRewardedAdLoader
import com.thezayin.ads.utils.AdUnit

class GoogleManager() {
    private val debug get() = BuildConfig.DEBUG
    private var native: GoogleNativeAdLoader? = null
    private var rewardedAd: GoogleRewardedAdLoader? = null
    private var interstitialAd: GoogleInterstitialAdLoader? = null
    private var appOpenAd: GoogleAppOpenAdLoader? = null

    private val testDeviceIds: List<String> = listOf(
        AdRequest.DEVICE_ID_EMULATOR, "990C1C4A58DB7FED6AF5D9A33E3DD1FF",//Samsung,
        "65B571F43583ED2ABB211D2965BE3E11"
    )

    fun init(activity: Activity) {
        MobileAds.initialize(activity)
        if (debug) MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        )
        native = GoogleNativeAdLoader(activity, AdUnit.native.resolve(debug))
        rewardedAd = GoogleRewardedAdLoader(activity, AdUnit.rewarded.resolve(debug))
        interstitialAd = GoogleInterstitialAdLoader(activity, AdUnit.interstitial.resolve(debug))
        appOpenAd = GoogleAppOpenAdLoader(activity, AdUnit.appOpen.resolve(debug))
    }

    fun getNativeAd(onAdLoaded: (NativeAd?) -> Unit) {
        native?.loadAd { ad ->
            onAdLoaded(ad)
        }
    }

    fun getInterstitialAd(onLoading: () -> Unit, onAdReady: (InterstitialAd?) -> Unit) {
        interstitialAd?.loadAd(
            onAdLoaded = { interstitialAd ->
                onAdReady(interstitialAd)
            }, onAdLoading = {
                onLoading()
            }, onAdFailed = {
                onAdReady(null)
            }) ?: run {
            onAdReady(null)
        }
    }

    fun getRewardedAd(onLoading: () -> Unit, onAdReady: (RewardedAd?) -> Unit) {
        rewardedAd?.loadAd(
            onAdLoaded = { rewardedAd ->
                onAdReady(rewardedAd)
            }, onAdLoading = {
                onLoading()
            }, onAdFailed = {
                onAdReady(null)
            }) ?: run {
            onAdReady(null)
        }
    }

    fun getAppOpenAd(onLoading: () -> Unit, onAdReady: (AppOpenAd?) -> Unit) {
        appOpenAd?.loadAd(
            onAdLoaded = { appOpenAd ->
                onAdReady(appOpenAd)
            }, onAdLoading = {
                onLoading()
            }, onAdFailed = {
                onAdReady(null)
            }) ?: run {
            onAdReady(null)
        }
    }
}