package com.example.moviesapptask.ui.features.movieDetails

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.example.moviesapptask.common.manager.navigation.MoviesNavDestination
import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.domain.usecases.movieDetails.MovieDetailsUseCase
import com.example.moviesapptask.ui.shared.base.BaseViewModel
import com.example.moviesapptask.ui.shared.uimodel.LoadingState
import com.example.moviesapptask.ui.shared.uimodel.MovieDetailsUIModel
import com.example.moviesapptask.ui.shared.uimodel.toMovieDetailsUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    application: Application,
    backStackEntryArguments: Bundle?,
    private val movieDetailsUseCase: MovieDetailsUseCase
) : BaseViewModel(
    application = application,
) {

    private val _movieDetails = MutableStateFlow<MovieDetailsUIModel?>(null)
    var movieDetails = _movieDetails.asStateFlow()

    private val _movieDetailsArguments =
        MoviesNavDestination.MovieDetails.parseArguments(backStackEntryArguments)

    init {
        val c =_movieDetailsArguments.movieId
        var x =c
        fetchMovieDetails(_movieDetailsArguments.movieId)
    }

    private fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            loadingState.value = LoadingState.LOADING
            when (val response = movieDetailsUseCase.invoke(movieId.toInt())) {
                is Resource.Success -> {
                    if (response.data != null) {
                        _movieDetails.value = response.data.toMovieDetailsUIModel()
                    } else {
                        handleServerError(response.errorCode ?: "")
                        onBackButtonClicked()
                    }
                }

                is Resource.Error -> {
                    handleServerError(response.errorCode ?: "")
                }
            }.also {
                loadingState.value = LoadingState.DONE
            }
        }
    }

    fun onBackButtonClicked() {
        navController?.popBackStack()
    }
}