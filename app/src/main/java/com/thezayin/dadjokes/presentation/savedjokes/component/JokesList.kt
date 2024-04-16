package com.thezayin.dadjokes.presentation.savedjokes.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.presentation.activity.dialogs.LoadingDialog
import com.thezayin.dadjokes.presentation.destinations.SavedJokeDetailScreenDestination
import com.thezayin.dadjokes.presentation.savedjokes.SaveViewModel
import kotlinx.coroutines.launch

@Composable
fun JokesList(
    saveViewModel: SaveViewModel,
    navigator: DestinationsNavigator
) {
    val isLoading = saveViewModel.isLoading.collectAsState().value.isLoading.value
    val jokes = saveViewModel.getAllJokes.collectAsState().value
    val scope = rememberCoroutineScope()

    if (isLoading) {
        LoadingDialog()
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(jokes.joke.size) { index ->
                SavedJokeTextCard(jokesModel = jokes.joke[index]) {
                    scope.launch {
                        navigator.navigate(
                            SavedJokeDetailScreenDestination(
                                id = jokes.joke[index].id
                            )
                        )
                    }
                }
            }
        }
    }
}