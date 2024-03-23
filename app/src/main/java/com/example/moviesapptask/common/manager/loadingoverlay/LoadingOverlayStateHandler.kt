package com.example.moviesapptask.common.manager.loadingoverlay

import kotlinx.coroutines.flow.StateFlow

interface LoadingOverlayStateHandler {
    val showLoading: StateFlow<Boolean>
    fun setShowLoading(show: Boolean)
}