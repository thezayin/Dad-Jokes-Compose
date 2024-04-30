package com.thezayin.dadjokes.presentation.iap

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thezayin.core.R
import com.thezayin.dadjokes.presentation.iap.component.IapBottomButton
import com.thezayin.dadjokes.presentation.iap.component.IapFeatureComponent

@Preview
@Composable
fun IAPScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_iap),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 45.dp, end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_cross),
                    contentDescription = null,
                    modifier = Modifier.size(28.dp),
                    contentScale = ContentScale.FillBounds
                )
            }
            IapFeatureComponent(modifier = Modifier)
        }
        IapBottomButton(modifier = Modifier.align(Alignment.BottomCenter))
    }
}