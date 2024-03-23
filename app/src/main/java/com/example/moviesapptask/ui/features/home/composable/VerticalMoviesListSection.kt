package com.example.moviesapptask.ui.features.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesapptask.ui.shared.composables.loading.GeneralLoading
import com.example.moviesapptask.ui.shared.uimodel.LoadingState
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import com.example.moviesapptask.ui.shared.uimodel.moviesListItemMock

@Composable
fun VerticalMoviesListSection(
    modifier: Modifier = Modifier,
    movieList: List<MoviesUIModel>,
    loadingState: LoadingState,
    onMovieSelected: (String) -> Unit
) {
    if (loadingState == LoadingState.LOADING) {
        GeneralLoading(modifier.fillMaxSize())
    } else {
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            movieList.forEach { movie ->
                VerticalMovieItem(movie = movie, onMovieSelected = onMovieSelected)
            }

        }
    }

}

@Composable
fun VerticalMovieItem(
    modifier: Modifier = Modifier,
    movie: MoviesUIModel,
    onMovieSelected: (String) -> Unit
) {
    val moviePosterWidth = 100.dp
    Row(modifier = modifier) {
        MoviePosterView(
            moviePosterItemWidth = moviePosterWidth,
            movie = movie,
            onMovieSelected = onMovieSelected
        )
        Column(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = movie.title ?: "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text =  movie.releaseDate?.substring(0, 4)?:"",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VerticalMoviesListSectionPreview() {
    VerticalMoviesListSection(
        modifier = Modifier,
        movieList = listOf(moviesListItemMock(), moviesListItemMock(), moviesListItemMock()),
        loadingState = LoadingState.DONE,
        onMovieSelected = {}
    )
}
