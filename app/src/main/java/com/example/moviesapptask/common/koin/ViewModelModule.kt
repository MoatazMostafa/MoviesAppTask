package com.example.moviesapptask.common.koin

import android.os.Bundle
import com.example.moviesapptask.ui.features.home.HomeViewModel
import com.example.moviesapptask.ui.features.host.MainViewModel
import com.example.moviesapptask.ui.features.movieDetails.MovieDetailsViewModel
import com.example.moviesapptask.ui.features.splash.SplashViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * This property is used to create ViewModels and inject needed parameters
 * */
val viewModelModule = module {

    viewModel {
        MainViewModel(application = androidApplication())
    }

    viewModel {
        HomeViewModel(
            application = androidApplication(),
            moviesUseCase = get()
        )
    }

    viewModel {(arguments: Bundle?) ->
        MovieDetailsViewModel(
            application = androidApplication(),
            backStackEntryArguments = arguments,
            movieDetailsUseCase = get()
        )
    }

    viewModel {
        SplashViewModel(application = androidApplication(),)
    }
}
