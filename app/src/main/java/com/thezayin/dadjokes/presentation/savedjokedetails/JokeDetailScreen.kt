package com.thezayin.dadjokes.presentation.savedjokedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.activity.dialogs.LoadingDialog
import com.thezayin.dadjokes.presentation.home.component.JokeTextCard
import com.thezayin.dadjokes.presentation.savedjokedetails.compoenents.SavedCardBottomButtons
import com.thezayin.dadjokes.presentation.savedjokedetails.compoenents.DetailsTopBar
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import org.koin.compose.koinInject

@Composable
@Destination
fun SavedJokeDetailScreen(
    navigator: DestinationsNavigator,
    id: String
) {
    val context = LocalContext.current
    val saveViewModel: SaveViewModel = koinInject()

    LaunchedEffect(Unit) {
        saveViewModel.getJokeById(id)
    }

    val isLoading = saveViewModel.isLoading.collectAsState().value.isLoading.value

    if (isLoading) {
        LoadingDialog()
    }

    val jokesModel = saveViewModel.getJokeById.collectAsState().value.joke

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(color = colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            DetailsTopBar(navigator = navigator, modifier = Modifier.padding(top = 20.dp))
            JokeTextCard(joke = jokesModel, modifier = Modifier.weight(0.6f))
            SavedCardBottomButtons(
                navigator = navigator,
                joke = jokesModel,
                saveViewModel = saveViewModel,
                context = context,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .weight(0.2f)
            )
        }
    }
}