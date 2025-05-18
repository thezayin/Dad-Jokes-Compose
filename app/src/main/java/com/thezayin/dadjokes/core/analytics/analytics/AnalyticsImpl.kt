package com.thezayin.dadjokes.core.analytics.analytics

import android.annotation.SuppressLint
import com.google.firebase.analytics.FirebaseAnalytics
import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent
import timber.log.Timber


class AnalyticsImpl(
    private val analytics: FirebaseAnalytics,
) : Analytics {
    @SuppressLint("BinaryOperationInTimber")
    override fun logEvent(event: AnalyticsEvent) {
        Timber.tag("Analytics")
            .d("FirebaseAnalyticsRepository eventName...." + event.event + " arguments... " + event.args + " ")

        event.event?.let { eventName ->
            analytics.logEvent(eventName, event.args)
        }
    }
}