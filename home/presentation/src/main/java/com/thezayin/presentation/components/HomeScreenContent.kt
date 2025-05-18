package com.thezayin.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.common.ErrorDialog
import com.thezayin.common.LoadingDialog
import com.thezayin.common.NetworkDialog
import com.thezayin.framework.lifecycles.ComposableLifecycle
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.lang.Error

@Composable
fun HomeScreenContent(
    modifier: Modifier,
    isLoading: Boolean,
    showError: Boolean,
    nativeAd: NativeAd?,
    showBottomAd: Boolean,
    showLoadingAd: Boolean,
    coroutineScope: CoroutineScope,
    networkStatus: MutableState<Boolean>,
    fetchNativeAd: () -> Unit,
    dismissErrorDialog: () -> Unit,
    navigateToSettingScreen: () -> Unit,
    navigateToSavedJokesScreen: () -> Unit,
    navigateUp: () -> Unit,
    saveCurrentJoke: () -> Unit,
    deleteCurrentJoke: () -> Unit,
    shareCurrentJoke: () -> Unit,
    copyCurrentJoke: () -> Unit,
) {

    // Lifecycle event handling for fetching native ads periodically
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                coroutineScope.launch {
                    while (isActive) {
                        fetchNativeAd()
                        delay(20000L) // Fetch a new ad every 20 seconds
                    }
                }
            }

            else -> Unit // No action needed for other lifecycle events
        }
    }

    // Show network connectivity dialog if needed
    if (networkStatus.value) {
        NetworkDialog(showDialog = { networkStatus.value = it })
    }
    // Show error dialog if there is an error
    if (showError) {
        ErrorDialog(
            showDialog = { dismissErrorDialog() },
            callback = {},
            error = "Unstable internet connection"
        )
    }

    // Display loading dialog with optional native ad
    if (isLoading) {
        LoadingDialog(
            ad = {
                GoogleNativeAd(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    nativeAd = nativeAd,
                    style = GoogleNativeAdStyle.Small
                )
            },
            showAd = showLoadingAd
        )
    }

    // Main layout with Scaffold
    Scaffold(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            HomeTopBar(
                modifier = Modifier,
                navigateToSettingScreen = navigateToSettingScreen,
                navigateToSavedJokesScreen = navigateToSavedJokesScreen
            )
        },
        bottomBar = {
            if (showBottomAd) {
                GoogleNativeAd(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = GoogleNativeAdStyle.Small,
                    nativeAd = nativeAd
                )
            }
        }
    ) { padding ->
    }
}