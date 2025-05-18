package com.thezayin.dadjokes.core.common

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.thezayin.dadjokes.core.framework.remote.RemoteConfig
import org.koin.compose.koinInject

@Composable
fun BannerAd(
    showAd: Boolean,
) {
    val remoteConfig = koinInject<RemoteConfig>()
    if (!showAd) return
    AndroidView(
        factory = { context ->
            AdView(context).apply {
                adUnitId = remoteConfig.bannerAdId
                setAdSize(AdSize.FULL_BANNER)
                val extras = Bundle().apply {
                    putString(
                        "collapsible",
                        "bottom"
                    )
                }
                val adRequest = AdRequest.Builder()
                    .addNetworkExtrasBundle(
                        AdMobAdapter::class.java,
                        extras
                    )
                    .build()
                loadAd(adRequest)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    )
}
