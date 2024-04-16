package com.thezayin.dadjokes.presentation.savedjokes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.activity.dialogs.LoadingDialog
import com.thezayin.dadjokes.presentation.savedjokes.component.DeleteComponent
import com.thezayin.dadjokes.presentation.savedjokes.component.JokesList
import com.thezayin.dadjokes.presentation.savedjokes.component.SavedScreenTopBar
import org.koin.compose.koinInject

@Destination
@Composable
fun SavedJokesScreen(
    navigator: DestinationsNavigator
) {
    val saveViewModel: SaveViewModel = koinInject()
    val isLoading = saveViewModel.isLoading.collectAsState().value.isLoading.value
    if (isLoading) {
        LoadingDialog()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        SavedScreenTopBar(navigator = navigator)
        DeleteComponent(saveViewModel = saveViewModel)
        JokesList(saveViewModel = saveViewModel, navigator = navigator)
    }
}