package com.thezayin.dadjokes.screens.saved.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.thezayin.dadjokes.core.common.TopBar
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel
import com.thezayin.dadjokes.screens.saved.presentation.state.SaveJokeState
import ir.kaaveh.sdpcompose.sdp

@Composable
fun SavedJokesScreenContent(
    state: SaveJokeState,
    showBanner: Boolean,
    navigateBack: () -> Unit,
    onDeleteClick: () -> Unit,
    onClick: (String) -> Unit,
) {
    GlassComponent()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            Column {
                TopBar(onBackClick = navigateBack)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            onDeleteClick()
                        }) {
                        Icon(
                            modifier = Modifier.size(18.sdp),
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = colorResource(id = R.color.black)
                        )
                    }
                }
            }

        },
        bottomBar = {
            BannerAd(showBanner)
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
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

            if (state.jokes?.isEmpty() == true) {
                EmptyState()
                return@Column
            } else {
                state.jokes?.let {
                    JokesList(
                        jokesList = state.jokes, onClick = onClick
                    )
                } ?: EmptyState()
            }
        }
    }
}


@Preview
@Composable
fun EmptyStatePreview() {
    EmptyState()
}

@Preview
@Composable
fun PreviewJokesList() {
    JokesList(
        jokesList = listOf(
            JokesModel(
                id = "1",
                joke = "Why did the scarecrow win an award? Because he was outstanding in his field!",
                status = 200
            ),
            JokesModel(
                id = "2",
                joke = "I told my wife she was drawing her eyebrows too high. She looked surprised.",
                status = 200
            )
        ),
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewSavedJokeTextCard() {
    SavedJokeTextCard(
        jokesModel = JokesModel(
            id = "1",
            joke = "Why did the scarecrow win an award? Because he was outstanding in his field!",
            status = 200
        ),
        callBack = {}
    )
}