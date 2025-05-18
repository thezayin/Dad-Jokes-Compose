package com.thezayin.dadjokes.screens.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.dadjokes.R
import com.thezayin.dadjokes.core.common.BannerAd
import com.thezayin.dadjokes.screens.onboarding.model.OnboardingPage
import com.thezayin.start_up.onboarding.components.OnBoardNavButton
import com.thezayin.start_up.onboarding.components.PageIndicator
import ir.kaaveh.sdpcompose.sdp

@Composable
fun OnboardingContent(
    showAd: Boolean, currentPage: Int, onboardPages: List<OnboardingPage>, onNextClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = colorResource(R.color.white),
        bottomBar = {
            Column {
                Row(
                    modifier = Modifier
                        .background(colorResource(R.color.black))
                        .fillMaxWidth()
                        .padding(start = 25.sdp, end = 10.sdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    PageIndicator(
                        totalPages = onboardPages.size,
                        currentPage = currentPage,
                        modifier = Modifier
                    )
                    OnBoardNavButton(
                        modifier = Modifier.padding(top = 5.sdp, bottom = 5.sdp),
                    ) {
                        onNextClicked()
                    }
                }
                BannerAd(showAd)
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OnBoardImageView(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                image = onboardPages[currentPage].images,
            )
        }
    }
}

@Preview
@Composable
fun OnBoardNavButtonPreview() {
    OnBoardNavButton(
        onNextClicked = {})
}


@Preview
@Composable
fun PageIndicatorPreview() {
    Row(
        modifier = Modifier
            .background(colorResource(R.color.white))
            .padding(20.sdp)
    ) {
        PageIndicator(
            totalPages = 3,
            currentPage = 1,
            modifier = Modifier.padding(20.sdp)
        )
    }
}

@Preview
@Composable
fun OnboardingContentPreview() {
    OnboardingContent(
        showAd = false,
        currentPage = 0,
        onboardPages = listOf(
            OnboardingPage(
                images = R.drawable.ob_s1
            )
        ),
        onNextClicked = {}
    )
}

@Preview
@Composable
fun OnBoardImageViewPreview() {
    OnBoardImageView(
        modifier = Modifier.fillMaxSize(),
        image = R.drawable.ob_s1
    )
}