package com.thezayin.core.utils.extension

import android.app.Activity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.analytics.helpers.AnalyticsHelper

fun showInterstitialAd(
    googleManager: GoogleManager,
    activity: Activity,
    analytics: AnalyticsHelper,
    callBack: () -> Unit,
) {
    googleManager.createInterstitialAd()?.apply {
        fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                callBack()
            }

            override fun onAdImpression() {
                super.onAdImpression()
                analytics.logEvent(
                    AnalyticsEvent(
                        type = AnalyticsEvent.Types.AD_IMPRESSION,
                        extras = listOf(
                            AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.AD_TYPE, "appOpen ad"),
                        ),
                    ),
                )
            }

            override fun onAdFailedToShowFullScreenContent(error: AdError) {
                super.onAdFailedToShowFullScreenContent(error)
                callBack()
            }
        }
        show(activity)
    } ?: run { callBack() }
}

fun Activity?.showInterstitialAd(
    googleManager: GoogleManager,
    analytics: AnalyticsHelper,
    callback: () -> Unit
) {
    this?.let { showInterstitialAd(googleManager, this, analytics, callback) }
        ?: callback()
}