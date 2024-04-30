package com.thezayin.dadjokes.presentation.activity.appupdate

sealed interface AppUpdateStatus {
    data object Checking : AppUpdateStatus
    data object NotAvailable : AppUpdateStatus
    data object Available : AppUpdateStatus
    data class Failed(val error: String) : AppUpdateStatus
    data object Cancelled : AppUpdateStatus
    data object Downloaded : AppUpdateStatus
    data object Downloading : AppUpdateStatus
    data object Pending : AppUpdateStatus
    data object Unknown : AppUpdateStatus
    data object Installed : AppUpdateStatus
}