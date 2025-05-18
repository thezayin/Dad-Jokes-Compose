package com.thezayin.dadjokes.screens.ai.presentation.component

import DynamicResizableTextField
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.common.BannerAd
import com.thezayin.dadjokes.core.common.TopBar
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun JokeIdeasScreenContent(
    showAd: Boolean,
    onGenerateClick: (String) -> Unit,
    showError: Boolean,
    error: String,
    navigateBack: () -> Unit,
    isWriting: Boolean,
    writingProgress: String,
    isWritingCompleted: Boolean,
    jokeIdeas: List<AIContentModel>,
    onCopyClick: () -> Unit,
    onShareClick: () -> Unit
) {

    var description by remember { mutableStateOf("") }
    val isButtonEnabled = description.isNotBlank()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            TopBar(onBackClick = navigateBack)
        },
        bottomBar = {
            Column {
                if (!isWriting && !isWritingCompleted) {
                    GenerateButton(
                        enabled = isButtonEnabled, onClick = { onGenerateClick(description) })
                }
                BannerAd(showAd)
            }
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isWriting) {
                WhiteboardScreen(
                    isError = showError,
                    errorMessage = error,
                    writingProgress = writingProgress,
                    isWritingCompleted = isWritingCompleted,
                    jokeIdeas = jokeIdeas,
                    onCopyClick = onCopyClick,
                    onShareClick = onShareClick
                )
            } else {
                Text(
                    text = "Use our AI models to generate hilarious jokes. Fill out the form below and let the laughs begin!",
                    fontSize = 8.ssp,
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_bold)),
                    modifier = Modifier
                        .padding(top = 10.sdp)
                        .padding(horizontal = 15.sdp)
                )

                JokeDetails(
                    descriptionTitle = "Describe your joke idea",
                    jokePlaceholder = "Tell AI what kind of joke you want.",
                    onJokeChange = { description = it })
            }
        }
    }
}

@Preview
@Composable
fun PreviewJokeIdeasDisplay() {
    val sampleJokeIdeas = listOf(
        AIContentModel(
            "Why did the scarecrow win an award?",
            "Because he was outstanding in his field!"
        ),
        AIContentModel("Why don't skeletons fight each other?", "They don't have the guts."),
        AIContentModel("What do you call cheese that isn't yours?", "Nacho cheese!"),
    )
    JokeIdeasDisplay(
        jokeIdeas = sampleJokeIdeas,
        onCopyClick = {},
        onShareClick = {}
    )
}

@Preview
@Composable
fun PreviewJokeIdeaItem() {
    JokeIdeaItem(
        joke = AIContentModel(
            title = "Sample Title",
            description = "This is a sample description for the joke idea.",
            content = "Sample content",
            additionalInfo = "Additional info"
        ),
        onCopyClick = {},
        onShareClick = {}
    )
}

@Preview
@Composable
fun GenerateButtonPreview() {
    GenerateButton(onClick = {}, enabled = true)
}

@Preview
@Composable
fun AnimatedProgressTextPreview() {
    AnimatedProgressText(text = "Loading")
}

@Preview
@Composable
fun WhiteboardScreenPreview() {
    WhiteboardScreen(
        isError = false,
        errorMessage = "Error occurred",
        writingProgress = "Writing...",
        isWritingCompleted = true,
        jokeIdeas = listOf(
            AIContentModel("Joke 1", "Description 1"),
            AIContentModel("Joke 2", "Description 2")
        ),
        onCopyClick = {},
        onShareClick = {}
    )
}

@Preview
@Composable
fun PreviewDynamicResizableTextField() {
    val descriptionText = remember { mutableStateOf(TextFieldValue("")) }
    DynamicResizableTextField(
        descriptionText = descriptionText.value,
        onJokeChange = { descriptionText.value = it },
        jokePlaceholder = "Type your joke here..."
    )
}

@Preview
@Composable
fun JokeIdeasScreenContentPreview() {
    JokeIdeasScreenContent(
        showAd = true,
        onGenerateClick = {},
        showError = false,
        error = "",
        navigateBack = {},
        isWriting = false,
        writingProgress = "50%",
        isWritingCompleted = false,
        jokeIdeas = emptyList(),
        onCopyClick = {},
        onShareClick = {}
    )
}

@Preview
@Composable
fun JokeDetailsPreview() {
    JokeDetails(
        descriptionTitle = "Joke Ideas",
        jokePlaceholder = "Enter your joke ideas here...",
        onJokeChange = {}
    )
}