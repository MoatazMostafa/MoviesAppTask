package com.example.moviesapptask.ui.features.splash

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.moviesapptask.common.manager.navigation.MoviesNavDestination
import com.example.moviesapptask.ui.shared.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    application: Application,

) : BaseViewModel(
    application = application,
) {


    private var _isScreenClosed = MutableStateFlow(false)


    fun onStartSplash() {
        viewModelScope.launch {
            delay(3000)
            navController?.popBackStack()
            navController?.navigate(route = MoviesNavDestination.Home.navComposableDestination)
        }
    }

    fun onStopSplash() {
        _isScreenClosed.value = true
    }
}