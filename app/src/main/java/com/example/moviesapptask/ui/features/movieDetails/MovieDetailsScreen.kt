package com.example.moviesapptask.ui.features.movieDetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.moviesapptask.ui.features.movieDetails.composable.MovieDetailsContent
import com.example.moviesapptask.ui.shared.base.BaseScreen
import com.example.moviesapptask.ui.shared.composables.loading.GeneralLoading
import com.example.moviesapptask.ui.shared.uimodel.LoadingState

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    movieDetailsViewModel: MovieDetailsViewModel
) {
    BaseScreen(content = {
        when (movieDetailsViewModel.loadingStateValue.collectAsState().value) {
            LoadingState.LOADING -> GeneralLoading(modifier = modifier.fillMaxSize())
            else -> {
                movieDetailsViewModel.movieDetails.collectAsState().value?.let {
                    MovieDetailsContent(
                        modifier = modifier,
                        movieDetails = it,
                        onBackButtonClicked = movieDetailsViewModel::onBackButtonClicked
                    )
                }
            }
        }
    }, viewModel = movieDetailsViewModel)
}
