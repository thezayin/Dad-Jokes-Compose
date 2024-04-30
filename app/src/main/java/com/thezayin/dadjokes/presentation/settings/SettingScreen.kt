package com.thezayin.dadjokes.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.core.R
import com.thezayin.dadjokes.presentation.settings.component.SettingListComponent
import com.thezayin.dadjokes.presentation.settings.component.SettingScreenTopBar

@Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .background(color = colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .fillMaxSize()
        ) {
            SettingScreenTopBar(modifier = Modifier, navigator = navigator)
            SettingListComponent(modifier = Modifier, navigator = navigator)
        }
        Text(
            text = "v ${
                context.packageManager
                    .getPackageInfo(context.packageName, 0).versionName
            }",
            color = colorResource(id = R.color.text_color),
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        )
    }
}