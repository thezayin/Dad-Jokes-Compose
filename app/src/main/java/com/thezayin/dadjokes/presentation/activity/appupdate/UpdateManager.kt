package com.thezayin.dadjokes.presentation.activity.appupdate

import android.content.Context
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.annotation.RequiresApi
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.ktx.isFlexibleUpdateAllowed
import com.google.android.play.core.ktx.isImmediateUpdateAllowed
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await

@Suppress("DEPRECATION")
@RequiresApi(Build.VERSION_CODES.P)
class UpdateManager(activity: Context) {
    private val versionCode =
        activity.packageManager.getPackageInfo(activity.packageName, 0).longVersionCode
    private val manager = AppUpdateManagerFactory.create(activity)
    private val remoteConfig = Firebase.remoteConfig
    suspend fun updateType(): Int = runCatching {
        if (remoteConfig.getLong("force_update_version_code") == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_LONG) {
            remoteConfig.fetch(0).await()
            remoteConfig.activate().await()
        }
        return if (remoteConfig.getLong("force_update_version_code") >= versionCode) {
            AppUpdateType.IMMEDIATE
        } else AppUpdateType.FLEXIBLE
    }.getOrDefault(CHECK_UPDATE_FAILED)

    private val _status = MutableStateFlow<AppUpdateStatus>(AppUpdateStatus.Checking)
    val status: Flow<AppUpdateStatus> = _status.asStateFlow()

    suspend fun checkForUpdate(
        launcher: ActivityResultLauncher<IntentSenderRequest>
    ) {
        updateStatus(AppUpdateStatus.Checking)
        manager.registerListener { state ->
            handleInstallStatus(state.installStatus())
        }
        runCatching {
            val info = manager.appUpdateInfo.await()
            val isUpdateAvailable = info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            updateStatus(if (isUpdateAvailable) AppUpdateStatus.Available else AppUpdateStatus.NotAvailable)
            val isUpdateAllowed = when (updateType()) {
                AppUpdateType.FLEXIBLE -> info.isFlexibleUpdateAllowed
                AppUpdateType.IMMEDIATE -> info.isImmediateUpdateAllowed
                CHECK_UPDATE_FAILED -> {
                    updateStatus(AppUpdateStatus.Failed("Check for update failed!"))
                    true
                }

                else -> {
                    updateStatus(AppUpdateStatus.NotAvailable)
                    false
                }
            }
            val updateBuilder = AppUpdateOptions.newBuilder(updateType()).build()
            if (isUpdateAvailable && isUpdateAllowed) {
                manager.startUpdateFlowForResult(
                    info,
                    launcher,
                    updateBuilder
                )
            }
        }.onFailure { updateStatus(AppUpdateStatus.Failed("Something went wrong, update check failed!")) }
    }

    private fun handleInstallStatus(status: Int) {
        when (status) {
            InstallStatus.DOWNLOADED -> updateStatus(AppUpdateStatus.Downloaded)
            InstallStatus.FAILED -> updateStatus(AppUpdateStatus.Failed("APP Update Failed! Try Again."))
            InstallStatus.CANCELED -> updateStatus(AppUpdateStatus.Cancelled)
            InstallStatus.DOWNLOADING -> updateStatus(AppUpdateStatus.Downloading)
            InstallStatus.PENDING -> updateStatus(AppUpdateStatus.Pending)
            InstallStatus.UNKNOWN -> updateStatus(AppUpdateStatus.Unknown)
            InstallStatus.REQUIRES_UI_INTENT -> updateStatus(AppUpdateStatus.Unknown)
            InstallStatus.INSTALLED -> updateStatus(AppUpdateStatus.Installed)
            else -> updateStatus(AppUpdateStatus.NotAvailable)
        }
    }

    fun installUpdate() = manager.completeUpdate()
    fun updateStatus(status: AppUpdateStatus) = _status.update { status }

    private val CHECK_UPDATE_FAILED = -1
}