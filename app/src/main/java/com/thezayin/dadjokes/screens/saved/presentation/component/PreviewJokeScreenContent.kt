package com.thezayin.dadjokes.screens.saved.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
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
import com.thezayin.dadjokes.core.common.LoadingDialog
import com.thezayin.dadjokes.core.common.TopBar
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import com.thezayin.dadjokes.screens.saved.presentation.state.SaveJokeState

@Composable
fun PreviewJokeScreenContent(
    state: SaveJokeState,
    showBottomAd: Boolean,
    onDeleteClick: () -> Unit,
    navigateBack: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            Column {
                TopBar(
                    onBackClick = navigateBack
                )
            }
        },
        bottomBar = {
            BannerAd(showBottomAd)
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            state.errorMessage?.isNotBlank()?.let {
                if (!it) {
                    ErrorDialog(state.errorMessage)
                    return@Column
                }
            }

            if (state.isLoading) {
                LoadingDialog()
                return@Column
            }
            state.joke?.let { joke: JokesModel ->
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                ) {
                    PreviewJokeCard(
                        joke = joke.joke, deleteJoke = onDeleteClick
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewBottomButtonPreview() {
    PreviewBottomButton(
        joke = "This is a preview joke",
        deleteJoke = {}
    )
}

@Preview
@Composable
fun PreviewJokeCard() {
    PreviewJokeCard(
        joke = "Why did the scarecrow win an award? Because he was outstanding in his field!",
        deleteJoke = {}
    )
}