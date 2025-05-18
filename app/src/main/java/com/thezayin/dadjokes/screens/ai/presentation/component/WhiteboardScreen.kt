package com.thezayin.dadjokes.screens.ai.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.screens.ai.domain.model.AIContentModel
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun WhiteboardScreen(
    isError: Boolean,
    errorMessage: String,
    writingProgress: String,
    isWritingCompleted: Boolean,
    jokeIdeas: List<AIContentModel>,
    onCopyClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.sdp)
            .padding(top = 30.sdp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        if (!isWritingCompleted) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.sdp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_magic),
                    contentDescription = "Loading Icon",
                    modifier = Modifier
                        .size(24.sdp)
                        .padding(end = 8.sdp)
                )
                AnimatedProgressText(text = writingProgress)
            }
        } else {
            if (isError) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 16.sdp)
                        .wrapContentHeight()
                ) {

                    Text(
                        text = errorMessage,
                        fontSize = 8.ssp,
                        color = colorResource(id = R.color.red),
                        fontFamily = FontFamily(Font(R.font.noto_sans_regular))
                    )
                    Icon(
                        imageVector = Icons.Default.Block,
                        contentDescription = "Error Icon",
                        modifier = Modifier
                            .size(24.sdp)
                            .padding(end = 8.sdp)
                    )
                }
            } else {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.sdp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorResource(R.color.background)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(15.sdp)
                            .wrapContentHeight()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 16.sdp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_magic),
                                contentDescription = "Thought Icon",
                                modifier = Modifier
                                    .size(18.sdp)
                                    .padding(end = 8.sdp)
                            )
                            Text(
                                text = "Thought for few seconds",
                                fontSize = 8.ssp,
                                color = colorResource(id = R.color.black)
                            )
                        }

                        JokeIdeasDisplay(
                            jokeIdeas = jokeIdeas,
                            onCopyClick = onCopyClick,
                            onShareClick = onShareClick
                        )
                    }
                }
            }
        }
    }
}