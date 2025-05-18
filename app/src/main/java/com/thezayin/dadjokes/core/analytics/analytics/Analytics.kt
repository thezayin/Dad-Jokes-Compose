package com.thezayin.dadjokes.core.analytics.analytics

import com.thezayin.dadjokes.core.analytics.events.AnalyticsEvent

interface Analytics {
    fun logEvent(event: AnalyticsEvent)
}