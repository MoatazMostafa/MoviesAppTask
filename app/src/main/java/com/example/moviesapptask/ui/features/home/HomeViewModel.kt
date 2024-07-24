package com.example.moviesapptask.ui.features.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.moviesapptask.common.manager.navigation.MoviesNavDestination
import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.domain.model.MoviesListType
import com.example.moviesapptask.domain.usecases.movies.AddMovieFavouriteListUseCase
import com.example.moviesapptask.domain.usecases.movies.GetFavouriteMoviesListUseCase
import com.example.moviesapptask.domain.usecases.movies.MoviesUseCase
import com.example.moviesapptask.domain.usecases.movies.RemoveMovieFavouriteListUseCase
import com.example.moviesapptask.ui.shared.base.BaseViewModel
import com.example.moviesapptask.ui.shared.uimodel.LoadingState
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import com.example.moviesapptask.ui.shared.uimodel.toMoviesUIModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(
    application: Application,
    private val moviesUseCase: MoviesUseCase,
    private val addMovieFavouriteListUseCase: AddMovieFavouriteListUseCase,
    private val removeMovieFavouriteListUseCase: RemoveMovieFavouriteListUseCase,
    private val getFavouriteMoviesListUseCase: GetFavouriteMoviesListUseCase,
) : BaseViewModel(
    application = application,
) {

    private val _discoverMoviesList = MutableStateFlow<List<MoviesUIModel>>(emptyList())
    var discoverMoviesList = _discoverMoviesList.asStateFlow()

    private var _shownMoviesList = MutableStateFlow<List<MoviesUIModel>>(emptyList())
    var shownMoviesList = _shownMoviesList.asStateFlow()

    private val _nowPlayingMoviesList = MutableStateFlow<List<MoviesUIModel>>(emptyList())
    private val _popularMoviesList = MutableStateFlow<List<MoviesUIModel>>(emptyList())
    private val _upcomingMoviesList = MutableStateFlow<List<MoviesUIModel>>(emptyList())
    private val _favouriteMoviesList = MutableStateFlow<List<MoviesUIModel>>(emptyList())

    private var _selectedTabIndex = MutableStateFlow(0)
    val selectedTabIndex = _selectedTabIndex.asStateFlow()

    private val _discoverLoadingState = MutableStateFlow(LoadingState.NOT_FETCHED)
    val discoverLoadingStateValue = _discoverLoadingState.asStateFlow()

    private val _tabsLoadingState = MutableStateFlow(LoadingState.NOT_FETCHED)
    val tabsLoadingStateValue = _tabsLoadingState.asStateFlow()

    init {
        fetchDiscoverMoviesList()
        fetchNowPlayingMoviesList()
        fetchFavouriteMoviesList()
    }

    // Private functions
    private fun fetchDiscoverMoviesList() {
        viewModelScope.launch {
            _discoverLoadingState.value = LoadingState.LOADING
            when (val response = moviesUseCase.invoke(MoviesListType.DISCOVER)) {
                is Resource.Success -> {
                    _discoverMoviesList.value =
                        response.data?.map { it.toMoviesUIModel() } ?: emptyList()
                }

                is Resource.Error -> {
                    handleServerError(response.errorCode ?: "")
                }
            }.also {
                _discoverLoadingState.value = LoadingState.DONE
            }
        }
    }

    private fun fetchNowPlayingMoviesList() {
        viewModelScope.launch {
            _tabsLoadingState.value = LoadingState.LOADING
            when (val response = moviesUseCase.invoke(MoviesListType.NOW_PLAYING)) {
                is Resource.Success -> {
                    _nowPlayingMoviesList.value =
                        response.data?.map { it.toMoviesUIModel() } ?: emptyList()
                }

                is Resource.Error -> {
                    handleServerError(response.errorCode ?: "")
                }
            }.also {
                _shownMoviesList.value = _nowPlayingMoviesList.value
                _tabsLoadingState.value = LoadingState.DONE
                updateShownFavouriteMoviesList()
            }
        }
    }

    private fun fetchPopularMoviesList() {
        viewModelScope.launch {
            _tabsLoadingState.value = LoadingState.LOADING
            when (val response = moviesUseCase.invoke(MoviesListType.POPULAR)) {
                is Resource.Success -> {
                    _popularMoviesList.value =
                        response.data?.map { it.toMoviesUIModel() } ?: emptyList()
                }

                is Resource.Error -> {
                    handleServerError(response.errorCode ?: "")
                }
            }.also {
                _shownMoviesList.value = _popularMoviesList.value
                _tabsLoadingState.value = LoadingState.DONE
                updateShownFavouriteMoviesList()
            }
        }
    }

    private fun fetchUpcomingMoviesList() {
        viewModelScope.launch {
            _tabsLoadingState.value = LoadingState.LOADING
            when (val response = moviesUseCase.invoke(MoviesListType.UPCOMING)) {
                is Resource.Success -> {
                    _upcomingMoviesList.value =
                        response.data?.map { it.toMoviesUIModel() } ?: emptyList()
                }

                is Resource.Error -> {
                    handleServerError(response.errorCode ?: "")
                }
            }.also {
                _shownMoviesList.value = _upcomingMoviesList.value
                _tabsLoadingState.value = LoadingState.DONE
                updateShownFavouriteMoviesList()
            }
        }
    }

    private fun fetchFavouriteMoviesList() {
        viewModelScope.launch {
            _favouriteMoviesList.value = getFavouriteMoviesListUseCase.invoke()
            updateShownFavouriteMoviesList()
        }
    }

    private fun updateShownFavouriteMoviesList() {
        _shownMoviesList.value.map {
            val favouriteMovie = _favouriteMoviesList.value.firstOrNull { item -> item.id == it.id }
            if (favouriteMovie != null) {
                it.isFavorite = true
            }
        }
    }

    // Public functions
    fun onFavouriteClick(movie: MoviesUIModel, isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) {
                removeMovieFavouriteListUseCase.invoke(movie)
            } else {
                addMovieFavouriteListUseCase.invoke(movie)
            }
            fetchFavouriteMoviesList()
        }
    }

    fun onTabSelected(tabIndex: Int) {
        _selectedTabIndex.value = tabIndex
        when (tabIndex) {
            0 -> fetchNowPlayingMoviesList()
            1 -> fetchPopularMoviesList()
            2 -> fetchUpcomingMoviesList()
        }
    }

    fun onMovieSelected(movieId: String) {
        navController?.navigate(route =
            MoviesNavDestination.MovieDetails.getNavigationRoute(movieDetailsId = movieId).route
        )
    }
}