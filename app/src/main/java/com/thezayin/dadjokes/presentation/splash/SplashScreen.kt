package com.thezayin.dadjokes.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.presentation.destinations.HomeScreenDestination
import com.thezayin.dadjokes.presentation.activity.dialogs.NetworkDialog
import com.thezayin.dadjokes.presentation.utils.checkForInternet
import kotlinx.coroutines.delay

@Composable
@RootNavGraph(start = true)
@Destination
fun SplashScreen(
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current
    var checkNetwork by remember { mutableStateOf(false) }

    if (checkNetwork) {
        NetworkDialog(showDialog = { checkNetwork = it })
    }

    LaunchedEffect(key1 = Unit) {
        if (!context.checkForInternet()) {
            delay(5000L)
            checkNetwork = true
        } else {
            delay(4000L)
            navigator.navigate(HomeScreenDestination)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .background(color = colorResource(id = R.color.background))
    ) {
        Text(
            text = "Daddy's Daily Jokes",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 25.dp),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 1.1.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            )
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 25.dp, vertical = 25.dp)
                .wrapContentWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Card(
                modifier = Modifier.padding(end = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_main),
                    contentDescription = null,
                )
            }

            Spacer(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.black))
                    .width(1.dp)
                    .height(40.dp)
            )

            Text(
                text = "Daily Laughter Guaranteed",
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}