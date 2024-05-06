package com.thezayin.dadjokes.presentation.activity

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.nativead.NativeAd
import com.thezayin.ads.GoogleManager
import com.thezayin.analytics.helpers.AnalyticsHelper
import com.thezayin.dadjokes.presentation.activity.appupdate.AppUpdateStatus
import com.thezayin.dadjokes.presentation.activity.appupdate.UpdateManager
import com.thezayin.framework.extension.ads.native.createNativeInterstitialAd
import com.thezayin.framework.remote.RemoteConfig
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

sealed interface UpdateMessage {
    data class Error(val message: String) : UpdateMessage
    data object Success : UpdateMessage
}


class MainViewModel(
    private val updateManager: UpdateManager,
    val googleManager: GoogleManager,
    val analyticsHelper: AnalyticsHelper,
    val remoteConfig: RemoteConfig
) : ViewModel() {

    private val updateStatus = updateManager.status
    private val _showUpdateMessage = Channel<UpdateMessage>()
    val showUpdateMessage = _showUpdateMessage.receiveAsFlow()

    var nativeAd = mutableStateOf<NativeAd?>(null)
        private set

    init {
        handleAppUpdateStatus()
    }

    private fun handleAppUpdateStatus() = viewModelScope.launch {
        updateManager.status.collect {
            when (it) {
                AppUpdateStatus.Cancelled -> {
                    _showUpdateMessage.send(UpdateMessage.Error("Update was cancelled!"))
                }

                AppUpdateStatus.Downloaded -> {
                    _showUpdateMessage.send(UpdateMessage.Success)
                }

                is AppUpdateStatus.Failed -> {
                    _showUpdateMessage.send(UpdateMessage.Error(it.error))
                }

                else -> Unit
            }
        }
    }

    fun getNativeAd() = viewModelScope.launch {
        nativeAd.value = googleManager.createNativeAd() ?: run {
            delay(3000)
            googleManager.createNativeAd()
        }
    }

    fun showNativeInterstitial(
        context: Context,
        callback: () -> Unit
    ) {
        viewModelScope.launch {
            googleManager.createNativeInterstitialAd(context, this) {
                callback()
            }
        }
    }
}