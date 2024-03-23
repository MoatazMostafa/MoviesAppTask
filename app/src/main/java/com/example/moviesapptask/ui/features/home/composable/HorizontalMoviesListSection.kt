package com.example.moviesapptask.ui.features.home.composable

import android.annotation.SuppressLint
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesapptask.R
import com.example.moviesapptask.ui.shared.composables.loading.GeneralLoading
import com.example.moviesapptask.ui.shared.uimodel.LoadingState
import com.example.moviesapptask.ui.shared.uimodel.moviesListItemMock
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import kotlinx.coroutines.flow.MutableStateFlow

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HorizontalMoviesListSection(
    modifier: Modifier = Modifier,
    movieList: List<MoviesUIModel>,
    loadingState: LoadingState,
    onMovieSelected: (String) -> Unit
) {
    if (loadingState == LoadingState.LOADING) {
        GeneralLoading(modifier = modifier
            .fillMaxWidth()
            .height(200.dp))
    } else {
        if (movieList.isEmpty())
            return
        val lazyRowListState = rememberLazyListState()
        val animationSpec = keyframes<Float> {
            durationMillis = 50000
        }

        val moviePosterWidth = 100.dp
        val currentIndex = remember { MutableStateFlow(0) }

        LaunchedEffect(currentIndex.collectAsState().value) {
            lazyRowListState.animateScrollBy(movieList.size * 216F, animationSpec)
        }

        Column(modifier.padding(top = 16.dp)) {
            LazyRow(
                Modifier.fillMaxWidth(),
                state = lazyRowListState
            ) {
                items(movieList.size) {
                    currentIndex.value = it
                    MoviePosterView(
                        moviePosterItemWidth = moviePosterWidth,
                        movie = movieList[it],
                        onMovieSelected = onMovieSelected
                    )
                }
            }
        }
    }
}

@Composable
fun MoviePosterView(
    modifier: Modifier = Modifier,
    moviePosterItemWidth: Dp,
    movie: MoviesUIModel,
    onMovieSelected: (String) -> Unit
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .height(200.dp)
            .width(moviePosterItemWidth)
            .clickable { onMovieSelected(movie.id.toString()) },
        shape = MaterialTheme.shapes.medium,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentScale = ContentScale.FillBounds,
            model = "${stringResource(id = R.string.tmdb_image_base_url)}${movie.posterPath}",
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HorizontalMoviesListSectionPreview() {
    HorizontalMoviesListSection(
        modifier = Modifier,
        movieList = listOf(moviesListItemMock(), moviesListItemMock(), moviesListItemMock()),
        loadingState = LoadingState.DONE,
        onMovieSelected = {}
    )
}