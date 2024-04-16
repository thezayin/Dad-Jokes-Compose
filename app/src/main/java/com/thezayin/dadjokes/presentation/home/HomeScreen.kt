package com.thezayin.dadjokes.presentation.home

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
import androidx.compose.runtime.collectAsState
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
import com.thezayin.dadjokes.presentation.home.component.CardBottomButtons
import com.thezayin.dadjokes.presentation.home.component.HomeTopBar
import com.thezayin.dadjokes.presentation.home.component.JokeTextCard
import com.thezayin.dadjokes.presentation.home.component.NextJokeButton
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import org.koin.compose.koinInject

@Composable
@Destination
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val homeViewModel: HomeViewModel = koinInject()
    val saveViewModel: SaveViewModel = koinInject()
    var checkNetwork by remember { mutableStateOf(false) }
    val jokeList = homeViewModel.jokeText.collectAsState().value.joke.value
    val isLoading = homeViewModel.isLoading.collectAsState().value.isLoading.value

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    if (isLoading) {
        LoadingDialog()
    }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(
            id = R.color.background
        ),
        topBar = {
            HomeTopBar(navigator = navigator, modifier = Modifier)
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding()
                .navigationBarsPadding()
                .background(color = colorResource(id = R.color.background))
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.Center
            ) {
                JokeTextCard(
                    joke = jokeList, modifier = Modifier.weight(0.6f)
                )
                CardBottomButtons(
                    jokesModel = jokeList,
                    saveViewModel = saveViewModel,
                    context = context,
                    modifier = Modifier.weight(0.1f)
                )
                Column(
                    modifier = Modifier
                        .padding(bottom = 50.dp)
                        .fillMaxWidth()
                        .weight(0.2f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    NextJokeButton(viewModel = homeViewModel, modifier = Modifier)
                }
            }
        }
    }
}
