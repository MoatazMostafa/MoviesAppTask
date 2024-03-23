package com.example.moviesapptask.ui.features.home.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapptask.R
import com.example.moviesapptask.ui.shared.composables.TabsContent
import com.example.moviesapptask.ui.shared.composables.loading.GeneralLoading
import com.example.moviesapptask.ui.shared.uimodel.LoadingState
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import com.example.moviesapptask.ui.shared.uimodel.moviesListItemMock
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    discoverLoadingState: LoadingState,
    tabsLoadingState: LoadingState,
    discoverMoviesList: List<MoviesUIModel>,
    tabsMoviesList: List<MoviesUIModel>,
    onSelectTab: (Int) -> Unit,
    selectedTabIndex: Int,
    onMovieSelected: (String) -> Unit
) {
    Column(modifier = modifier) {

        HorizontalMoviesListSection(
            movieList = discoverMoviesList,
            loadingState = discoverLoadingState,
            onMovieSelected = onMovieSelected
        )
        TabsContent(
            selectedTabIndex,
            listOf(
                stringResource(id = R.string.now_playing),
                stringResource(id = R.string.popular),
                stringResource(id = R.string.upcoming)
            ),
            onSelectTab
        )
        VerticalMoviesListSection(
            movieList = tabsMoviesList,
            loadingState = tabsLoadingState,
            onMovieSelected = onMovieSelected
        )
    }
}

@Preview
@Composable
fun MovieContentPreview() {
    MoviesAppTaskTheme {
        HomeContent(
            discoverLoadingState = LoadingState.DONE,
            tabsLoadingState = LoadingState.DONE,
            discoverMoviesList = listOf(moviesListItemMock(), moviesListItemMock()),
            tabsMoviesList = listOf(moviesListItemMock(), moviesListItemMock()),
            onSelectTab = { },
            selectedTabIndex = 1,
            onMovieSelected = { },
        )
    }
}
