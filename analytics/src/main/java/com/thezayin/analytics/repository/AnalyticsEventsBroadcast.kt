package com.thezayin.analytics.repository

import com.thezayin.analytics.dependencies.Analytics
import com.thezayin.analytics.events.AnalyticsEvent
import com.thezayin.analytics.qualifiers.AmplitudeAnalytics
import com.thezayin.analytics.qualifiers.GoogleAnalytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsEventsBroadcast @Inject constructor(
    @AmplitudeAnalytics private val amplitudeAnalytics: Analytics,
    @GoogleAnalytics private val googleAnalytics: Analytics,
) : Analytics {

    override fun logEvent(event: AnalyticsEvent) {
        when (event.event) {
            "INTERSTITIAL_AD,RATING, SCREEN_VIEW" -> return
        }
        amplitudeAnalytics.logEvent(event)
        googleAnalytics.logEvent(event)
    }
}