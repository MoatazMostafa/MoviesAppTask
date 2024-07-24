package com.example.moviesapptask.ui.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.moviesapptask.ui.features.home.composable.HomeContent
import com.example.moviesapptask.ui.shared.base.BaseScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    BaseScreen(content = {
        HomeContent(
            modifier = modifier,
            discoverLoadingState = homeViewModel.discoverLoadingStateValue.collectAsState().value,
            tabsLoadingState = homeViewModel.tabsLoadingStateValue.collectAsState().value,
            discoverMoviesList = homeViewModel.discoverMoviesList.collectAsState().value,
            shownMoviesList = homeViewModel.shownMoviesList.collectAsState().value,
            selectedTabIndex = homeViewModel.selectedTabIndex.collectAsState().value,
            onFavouriteClick = homeViewModel::onFavouriteClick,
            onSelectTab = homeViewModel::onTabSelected,
            onMovieSelected = homeViewModel::onMovieSelected,

        )
    }, viewModel = homeViewModel)
}
