package com.thezayin.dadjokes.presentation.savedjokes.component

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.analytics.helpers.LocalAnalyticsHelper
import com.thezayin.core.utils.extension.showInterstitialAd
import com.thezayin.dadjokes.presentation.activity.MainViewModel
import com.thezayin.dadjokes.presentation.activity.dialogs.LoadingDialog
import com.thezayin.dadjokes.presentation.destinations.SavedJokeDetailScreenDestination
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import kotlinx.coroutines.launch

@Composable
fun JokesList(
    mainViewModel: MainViewModel,
    saveViewModel: SaveViewModel,
    navigator: DestinationsNavigator
) {
    val isLoading = saveViewModel.isLoading.collectAsState().value.isLoading.value
    val jokesList = saveViewModel.getAllJokes.collectAsState().value.joke
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val analytics = LocalAnalyticsHelper.current

    if (isLoading) {
        LoadingDialog(mainViewModel)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 55.dp)

    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(jokesList.size) { index ->
                SavedJokeTextCard(jokesModel = jokesList[index]) {
                    scope.launch {
                        showInterstitialAd(
                            googleManager = saveViewModel.googleManager,
                            activity = activity,
                            analytics = analytics
                        ) {
                            navigator.navigate(
                                SavedJokeDetailScreenDestination(
                                    id = jokesList[index].id
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}