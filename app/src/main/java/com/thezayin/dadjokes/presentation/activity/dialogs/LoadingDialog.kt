package com.thezayin.dadjokes.presentation.activity.dialogs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import com.thezayin.core.R

import com.thezayin.dadjokes.presentation.activity.MainViewModel
import com.thezayin.dadjokes.presentation.activity.component.ComposableLifecycle
import com.thezayin.core.utils.nativead.GoogleNativeAd
import com.thezayin.core.utils.nativead.GoogleNativeAdStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun LoadingDialog(viewModel: MainViewModel) {

    val nativeAd = remember { viewModel.nativeAd }
    val scope = rememberCoroutineScope()

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                scope.launch {
                    while (this.isActive) {
                        viewModel.getNativeAd()
                        delay(20000L)
                    }
                }
            }

            else -> {
                Timber.tag("Native_Ad").d("Not Called")
            }
        }
    }

    Dialog(onDismissRequest = { }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = colorResource(id = R.color.background)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Please wait....",
                        fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                        color = colorResource(id = R.color.text_color),
                        fontSize = 16.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                            .padding(bottom = 20.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = colorResource(id = R.color.purple))
                    }
                    GoogleNativeAd(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        nativeAd = nativeAd.value,
                        style = GoogleNativeAdStyle.Small
                    )
                }
            }
        }
    }
}
