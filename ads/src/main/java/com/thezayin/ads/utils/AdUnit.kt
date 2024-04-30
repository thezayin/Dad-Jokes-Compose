package com.thezayin.ads.utils

class AdUnit private constructor(
    private val id: String, private val testId: String
) {
    companion object {
        val native = AdUnit("ca-app-pub-2913057115284606/6318095717", TEST_NATIVE_ID)
        val appOpen = AdUnit("ca-app-pub-2913057115284606/5432389454", TEST_APP_OPEN_ID)
        val rewarded = AdUnit("ca-app-pub-9781925194514571/7082127130", TEST_REWARDED_ID)
        val interstitial = AdUnit("ca-app-pub-2913057115284606/1009928712", TEST_INTERSTITIAL_ID)
    }

    fun resolve(debug: Boolean = false) = if (debug) this.testId else id
}

private const val TEST_NATIVE_ID = "ca-app-pub-3940256099942544/2247696110"
private const val TEST_INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712"
private const val TEST_APP_OPEN_ID = "ca-app-pub-3940256099942544/9257395921"
private const val TEST_REWARDED_ID = "ca-app-pub-3940256099942544/5224354917"