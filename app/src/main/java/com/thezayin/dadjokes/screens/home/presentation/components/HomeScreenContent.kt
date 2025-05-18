package com.thezayin.dadjokes.screens.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.common.BannerAd
import com.thezayin.dadjokes.core.common.ErrorDialog
import com.thezayin.dadjokes.core.common.GlassComponent
import com.thezayin.dadjokes.core.common.LoadingDialog
import com.thezayin.dadjokes.screens.home.presentation.state.HomeState
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import ir.kaaveh.sdpcompose.sdp

@Composable
fun HomeScreenContent(
    state: HomeState,
    showBottomAd: Boolean,
    nextJoke: () -> Unit,
    navigateToSettingScreen: () -> Unit,
    navigateToSavedJokesScreen: () -> Unit,
    saveCurrentJoke: () -> Unit,
    removeCurrentJoke: () -> Unit,
    navigateToAiScreen: () -> Unit = {},
) {
    GlassComponent()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            Column {
                HomeTopBar(
                    navigateToSettingScreen = navigateToSettingScreen,
                    navigateToSavedJokesScreen = navigateToSavedJokesScreen
                )
                AiCard(
                    onClick = navigateToAiScreen
                )
                Spacer(modifier = Modifier.size(20.sdp))
            }
        },
        bottomBar = {
            BannerAd(showBottomAd)
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (!state.isNetworkAvailable) {
                ErrorDialog(state.errorMessage)
                return@Column
            }

            if (state.isLoading) {
                LoadingDialog()
                return@Column
            }

            if (state.showErrorDialog) {
                ErrorDialog(state.errorMessage)
                return@Column
            }

            state.joke?.let { joke: JokesModel ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    JokeTextCard(
                        joke = joke.joke,
                        isLiked = state.isJokeLiked,
                        removeJoke = removeCurrentJoke,
                        saveCallBack = saveCurrentJoke
                    )
                    NextJokeButton(
                        callback = nextJoke
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    HomeScreenContent(
        state = HomeState(
            isLoading = false,
            isNetworkAvailable = true,
            showErrorDialog = false,
            errorMessage = "",
            joke = JokesModel(
                joke = "Why did the scarecrow win an award? Because he was outstanding in his field!",
                id = 1.toString(),
                status = 200
            ),
            isJokeLiked = false
        ),
        showBottomAd = true,
        nextJoke = {},
        navigateToSettingScreen = {},
        navigateToSavedJokesScreen = {},
        saveCurrentJoke = {},
        removeCurrentJoke = {}
    )
}

@Preview
@Composable
fun AiCardPreview() {
    AiCard()
}

@Preview
@Composable
fun CardBottomButtonsPreview() {
    CardBottomButtons(
        modifier = Modifier,
        isLiked = false,
        joke = "This is a test joke",
        saveJoke = {},
        removeJoke = {}
    )
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(
        navigateToSettingScreen = {},
        navigateToSavedJokesScreen = {}
    )
}

@Preview
@Composable
fun PreviewJokeTextCard() {
    JokeTextCard(
        modifier = Modifier,
        joke = "Why did the scarecrow win an award? Because he was outstanding in his field!",
        isLiked = false,
        saveCallBack = {},
        removeJoke = {}
    )
}


@Preview
@Composable
fun NextJokeButtonPreview() {
    NextJokeButton {}
}