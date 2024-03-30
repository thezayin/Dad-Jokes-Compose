package com.thezayin.dadjokes.presentation.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.activity.dialogs.LoadingDialog
import com.thezayin.dadjokes.presentation.activity.dialogs.NetworkDialog
import com.thezayin.dadjokes.presentation.home.component.ActionButtonsComponent
import com.thezayin.dadjokes.presentation.home.component.JokeCardComponent
import com.thezayin.dadjokes.presentation.home.component.NextButtonComponent
import com.thezayin.dadjokes.presentation.home.component.TopBarComponent
import com.thezayin.dadjokes.presentation.home.viewmodel.HomeViewModel
import org.koin.compose.koinInject

@Composable
@Destination
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val homeViewModel: HomeViewModel = koinInject()
    val context = LocalContext.current
    val jokeList = homeViewModel.jokeText.joke.value
    val isLoading = homeViewModel.isLoadingState.isLoading.value
    var checkNetwork by remember { mutableStateOf(false) }

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(color = colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center
        ) {
            TopBarComponent(navigator = navigator, modifier = Modifier.weight(0.1f))
            JokeCardComponent(joke = jokeList, modifier = Modifier.weight(0.6f))
            ActionButtonsComponent(
                joke = jokeList, context = context, modifier = Modifier.weight(0.1f)
            )
            Column(
                modifier = Modifier
                    .padding(bottom = 50.dp)
                    .fillMaxWidth()
                    .weight(0.2f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                NextButtonComponent(viewModel = homeViewModel, modifier = Modifier)
            }
        }
    }
}