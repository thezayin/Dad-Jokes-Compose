package com.thezayin.dadjokes.screens.saved.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thezayin.dadjokes.screens.saved.domain.model.JokesModel

@Composable
fun JokesList(
    jokesList: List<JokesModel>,
    onClick: (String) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(jokesList.size) { index ->
                SavedJokeTextCard(
                    jokesModel = jokesList[index],
                    callBack = {
                        onClick(jokesList[index].id)
                    }
                )
            }
        }
    }
}