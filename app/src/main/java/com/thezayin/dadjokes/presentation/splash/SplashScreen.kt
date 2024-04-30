package com.thezayin.dadjokes.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.analytics.helpers.LocalAnalyticsHelper
import com.thezayin.core.R
import com.thezayin.core.utils.checkForInternet
import com.thezayin.core.utils.extension.showInterstitialAd
import com.thezayin.core.utils.getActivity
import com.thezayin.dadjokes.presentation.activity.MainViewModel
import com.thezayin.dadjokes.presentation.activity.dialogs.NetworkDialog
import com.thezayin.dadjokes.presentation.destinations.HomeScreenDestination
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@Composable
@RootNavGraph(start = true)
@Destination
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    var checkNetwork by remember { mutableStateOf(false) }
    val mainViewModel: MainViewModel = koinInject()
    val googleManager = mainViewModel.googleManager
    val analytics = LocalAnalyticsHelper.current
    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    LaunchedEffect(key1 = Unit) {
        if (!context.checkForInternet()) {
            delay(5000L)
            checkNetwork = true
        } else {
            delay(5000L)
            context.getActivity()?.let { activity ->
                showInterstitialAd(
                    activity = activity,
                    googleManager = googleManager,
                    analytics = analytics
                ) {
                    navigator.navigate(HomeScreenDestination)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_splash),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
        )
        Text(
            text = "Dad Jokes",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 25.dp),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
        )
    }
}