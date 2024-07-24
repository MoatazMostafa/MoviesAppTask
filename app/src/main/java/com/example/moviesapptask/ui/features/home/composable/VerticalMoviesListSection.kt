package com.example.moviesapptask.ui.features.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moviesapptask.R
import com.example.moviesapptask.ui.shared.composables.loading.GeneralLoading
import com.example.moviesapptask.ui.shared.uimodel.LoadingState
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import com.example.moviesapptask.ui.shared.uimodel.moviesListItemMock

@Composable
fun VerticalMoviesListSection(
    modifier: Modifier = Modifier,
    movieList: List<MoviesUIModel>,
    loadingState: LoadingState,
    onMovieSelected: (String) -> Unit,
    onFavouriteClick: (MoviesUIModel, Boolean) -> Unit
) {
    if (loadingState == LoadingState.LOADING) {
        GeneralLoading(modifier.fillMaxSize())
    } else {
        Column(modifier = modifier.verticalScroll(rememberScrollState())) {
            if (movieList.isEmpty()) {
                Text(
                    text = "No Movies Found",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
            movieList.forEach { movie ->
                VerticalMovieItem(
                    movie = movie,
                    onMovieSelected = onMovieSelected,
                    onFavouriteClick = onFavouriteClick
                )
            }

        }
    }

}

@Composable
fun VerticalMovieItem(
    modifier: Modifier = Modifier,
    movie: MoviesUIModel,
    onMovieSelected: (String) -> Unit,
    onFavouriteClick: (MoviesUIModel, Boolean) -> Unit
) {
    val moviePosterWidth = 100.dp
    var isFavorite by remember { mutableStateOf(movie.isFavorite) }

    Row(modifier = modifier) {
        MoviePosterView(
            moviePosterItemWidth = moviePosterWidth,
            movie = movie,
            onMovieSelected = onMovieSelected
        )
        Column(modifier = Modifier.weight(1f).padding(top = 16.dp)) {
            Text(
                text = movie.title ?: "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = movie.releaseDate?.substring(0, 4) ?: "",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp).clickable {
            isFavorite = !isFavorite
            onFavouriteClick(movie, movie.isFavorite) }
        ) {
            if (isFavorite) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_heart_filled),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            } else {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_heart_outline),
                    contentDescription = "",
                    tint = Color.Unspecified
                )
            }
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
        onMovieSelected = {},
        onFavouriteClick = { _, _ -> }
    )
}
