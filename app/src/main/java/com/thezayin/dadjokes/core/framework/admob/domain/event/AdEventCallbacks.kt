package com.thezayin.dadjokes.core.framework.admob.domain.event

interface AdEventCallbacks {
    fun onAdImpression() {}
    fun onAdFailed(error: String) {}
    fun onAdClicked() {}
    fun onAdDismissed() {}
    fun onNextAction()
}