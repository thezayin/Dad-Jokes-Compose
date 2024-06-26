package com.thezayin.dadjokes.presentation.activity

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.helpers.AnalyticsHelper
import com.thezayin.framework.remote.RemoteConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//sealed interface UpdateMessage {
////    data class Error(val message: String) : UpdateMessage
////    data object Success : UpdateMessage
//}

@SuppressLint("NewApi")
class MainViewModel(
    val googleManager: GoogleManager,
    val analyticsHelper: AnalyticsHelper,
    val remoteConfig: RemoteConfig
) : ViewModel() {


    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

//    init {
////        handleAppUpdateStatus()
//    }


    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd() ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
    }
}