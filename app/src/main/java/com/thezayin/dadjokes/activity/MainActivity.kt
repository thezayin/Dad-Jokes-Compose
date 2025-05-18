package com.thezayin.dadjokes.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.MobileAds
import com.thezayin.dadjokes.core.analytics.analytics.Analytics
import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent
import com.thezayin.dadjokes.core.framework.admob.domain.repository.AppOpenAdManager
import com.thezayin.dadjokes.core.framework.remote.RemoteConfig
import com.thezayin.dadjokes.navigation.NavHost
import com.thezayin.dadjokes.theme.DadJokesTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val remoteConfig: RemoteConfig by inject()
    private val adManager: AppOpenAdManager by inject()
    private val analytics: Analytics by inject()
    var isAppFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        loadAd()
        setContent {
            DadJokesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController)
            }
        }
    }

    fun loadAd() {
        adManager.loadAd(this)
    }

    override fun onStart() {
        super.onStart()
        if (isAppFirstTime) {
            isAppFirstTime = false
            return
        }
        adManager.showAd(
            activity = this,
            showAd = remoteConfig.adOnResume,
            onNext = {},
            adImpression = {
                analytics.logEvent(
                    AnalyticsEvent.AdImpressionEvent(
                        adProvider = "adMob",
                        adType = "AppOpenAd",
                    )
                )
            }
        )
    }
}