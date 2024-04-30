package com.thezayin.dadjokes.presentation.home.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thezayin.core.R
import com.thezayin.core.utils.copyText
import com.thezayin.core.utils.share

@Composable
fun CardBottomButtons(
    modifier: Modifier,
    from: String,
    joke: String,
    callback: () -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp, top = 100.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_copy),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 20.dp)
                .size(24.dp)
                .clickable {
                    context.copyText(joke)
                },
        )
        Card(
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    callback()
                    Toast
                        .makeText(
                            context,
                            if (from == "home") "Joke saved" else "Deleted Successfully",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                },
            shape = RoundedCornerShape(100.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(100.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                colorResource(id = R.color.gradient_start),
                                colorResource(id = R.color.gradient_end)

                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = if (from == "home") painterResource(id = R.drawable.ic_like) else painterResource(
                        id = R.drawable.ic_delete
                    ),
                    contentDescription = null
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp)
                .size(24.dp)
                .clickable {
                    context.share(joke)
                },
        )
    }
}