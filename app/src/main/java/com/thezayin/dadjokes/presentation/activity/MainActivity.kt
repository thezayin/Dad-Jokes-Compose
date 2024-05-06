package com.thezayin.dadjokes.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.ramcosta.composedestinations.DestinationsNavHost
import com.thezayin.analytics.helpers.LocalAnalyticsHelper
import com.thezayin.dadjokes.presentation.NavGraphs
import com.thezayin.dadjokes.presentation.theme.DadJokesTheme
import com.thezayin.framework.utils.Constants.TOPIC
import com.thezayin.framework.extension.ads.showAppOpenAd
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        val analyticsHelper = viewModel.analyticsHelper
        viewModel.googleManager.init(this)
        viewModel.googleManager.initOnLastConsent()

        setContent {
            CompositionLocalProvider(
                LocalAnalyticsHelper provides analyticsHelper,
            ) {
                DadJokesTheme {
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        showAppOpenAd(
            activity = this,
            analytics = viewModel.analyticsHelper,
            googleManager = viewModel.googleManager
        )
    }
}