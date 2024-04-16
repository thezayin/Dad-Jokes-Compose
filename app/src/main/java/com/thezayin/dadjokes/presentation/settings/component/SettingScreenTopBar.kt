package com.thezayin.dadjokes.presentation.settings.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.dadjokes.R


@Composable
fun SettingScreenTopBar(
    modifier: Modifier,
    navigator: DestinationsNavigator
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navigator.navigateUp()
                        }
                )
            }
            Card(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_main),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Text(
                text = "Daily Dose of Dad Humor",
                modifier = Modifier.padding(top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )

        }
    }
}