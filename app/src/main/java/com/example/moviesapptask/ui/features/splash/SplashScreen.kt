package com.example.moviesapptask.ui.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviesapptask.ui.features.splash.composable.SplashContent
import com.example.moviesapptask.ui.shared.base.BaseScreen


@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    splashViewModel: SplashViewModel
) {
    BaseScreen(content = {
        SplashContent(
            modifier = modifier,
            onStart = splashViewModel::onStartSplash,
            onStop = splashViewModel::onStopSplash
        )
    }, viewModel = splashViewModel)
}