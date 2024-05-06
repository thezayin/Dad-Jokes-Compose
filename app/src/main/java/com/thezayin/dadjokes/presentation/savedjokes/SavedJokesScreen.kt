package com.thezayin.dadjokes.presentation.savedjokes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.core.R
import com.thezayin.dadjokes.presentation.activity.MainViewModel
import com.thezayin.dadjokes.presentation.activity.component.SavedScreenTopBar
import com.thezayin.dadjokes.presentation.activity.dialogs.LoadingDialog
import com.thezayin.dadjokes.presentation.savedjokes.component.JokesList
import com.thezayin.framework.nativead.GoogleNativeAd
import com.thezayin.framework.nativead.GoogleNativeAdStyle
import org.koin.compose.koinInject

@Destination
@Composable
fun SavedJokesScreen(
    navigator: DestinationsNavigator
) {
    val saveViewModel: SaveViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()
    val nativeAd = remember { mainViewModel.nativeAd }

    val isLoading = saveViewModel.isLoading.collectAsState().value.isLoading.value
    if (isLoading) {
        LoadingDialog(mainViewModel)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            SavedScreenTopBar(navigator = navigator, modifier = Modifier)
        },
        bottomBar = {
            if (mainViewModel.remoteConfig.showNativeAdOnSavedScreen) {
                GoogleNativeAd(
                    modifier = Modifier
                        .fillMaxWidth(),
                    nativeAd = nativeAd.value,
                    style = GoogleNativeAdStyle.Small
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = colorResource(id = R.color.background))
        ) {
            JokesList(
                saveViewModel = saveViewModel,
                navigator = navigator,
                mainViewModel = mainViewModel,
                remoteConfig = mainViewModel.remoteConfig
            )
        }
    }

}