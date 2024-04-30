package com.thezayin.dadjokes.presentation.savedjokedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.core.R
import com.thezayin.dadjokes.presentation.activity.MainViewModel
import com.thezayin.dadjokes.presentation.activity.component.ComposableLifecycle
import com.thezayin.dadjokes.presentation.activity.component.SavedScreenTopBar
import com.thezayin.dadjokes.presentation.activity.dialogs.LoadingDialog
import com.thezayin.dadjokes.presentation.home.component.JokeTextCard
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import com.thezayin.core.utils.nativead.GoogleNativeAd
import com.thezayin.core.utils.nativead.GoogleNativeAdStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import timber.log.Timber

@Composable
@Destination
fun SavedJokeDetailScreen(
    navigator: DestinationsNavigator,
    id: String
) {
    val saveViewModel: SaveViewModel = koinInject()
    val mainViewModel: MainViewModel = koinInject()
    val nativeAd = remember { mainViewModel.nativeAd }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        saveViewModel.getJokeById(id)
    }

    val isLoading = saveViewModel.isLoading.collectAsState().value.isLoading.value

    if (isLoading) {
        LoadingDialog(mainViewModel)
    }

    val jokesModel = saveViewModel.getJokeById.collectAsState().value.joke
    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        mainViewModel.getNativeAd()
                        delay(20000L)
                    }
                }
            }

            else -> {
                Timber.tag("Native_Ad").d("Not Called")
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(
            id = R.color.background
        ),
        topBar = {
            SavedScreenTopBar(navigator = navigator, modifier = Modifier)
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                GoogleNativeAd(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    nativeAd = nativeAd.value,
                    style = GoogleNativeAdStyle.Small
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding()
                .navigationBarsPadding()
                .background(color = colorResource(id = R.color.background))
        ) {
            JokeTextCard(
                jokeModel = jokesModel,
                modifier = Modifier.align(Alignment.Center),
                saveViewModel = saveViewModel,
                navigator = navigator,
                from = "savedScreen",
                id = id
            )
        }
    }
}