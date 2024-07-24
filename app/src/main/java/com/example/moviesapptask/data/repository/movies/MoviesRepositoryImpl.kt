package com.example.moviesapptask.data.repository.movies

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.datasource.local.LocalDataSource
import com.example.moviesapptask.data.datasource.remote.movies.MoviesDataSource
import com.example.moviesapptask.domain.model.MoviesDomainModel
import com.example.moviesapptask.domain.model.MoviesListType
import com.example.moviesapptask.domain.model.toMoviesDomainModel
import com.example.moviesapptask.domain.model.toMoviesDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MoviesRepositoryImpl(
    private val remoteMoviesDataSource: MoviesDataSource,
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MoviesRepository {
    private val maxPageNumber = 3

    override suspend fun addMovieFavouriteList(movie: MoviesDomainModel) {
        localDataSource.addMovieFavouriteList(movie.toMoviesDto())
    }

    override suspend fun removeMovieFavouriteList(movie: MoviesDomainModel) {
        localDataSource.removeMovieFavouriteList(movie.toMoviesDto())
    }

    override suspend fun getFavouriteMoviesList(): List<MoviesDomainModel> =
        localDataSource.getFavouriteMoviesList().map { it.toMoviesDomainModel() }

    override suspend fun getMoviesList(moviesListType: MoviesListType): Resource<List<MoviesDomainModel>> =
        when (moviesListType) {
            MoviesListType.DISCOVER -> getDiscoverMovies()
            MoviesListType.NOW_PLAYING -> getNowPlayingMovies()
            MoviesListType.POPULAR -> getPopularMovies()
            MoviesListType.UPCOMING -> getUpcomingMovies()
        }

    override suspend fun getDiscoverMovies(): Resource<List<MoviesDomainModel>> =
        withContext(ioDispatcher) {
            var page = localDataSource.getDiscoverLoadedPage()
            while (page <= maxPageNumber) {
                when (val response = remoteMoviesDataSource.getDiscoverMovies(page)) {
                    is Resource.Success -> {
                        localDataSource.updateCachedLocalDiscoverMoviesList(
                            response.data?.movies ?: emptyList()
                        )
                        localDataSource.setDiscoverLoadedPage(page)
                    }

                    else -> {
                        return@withContext Resource.Error(
                            errorCode = response.errorCode ?: "",
                            errorMessage = response.errorMessage
                        )
                    }
                }
                page++
            }
            return@withContext Resource.Success(
                localDataSource.getLocalDiscoverMoviesList()
                    .map { it.toMoviesDomainModel() })
        }

    override suspend fun getNowPlayingMovies(): Resource<List<MoviesDomainModel>> =
        withContext(ioDispatcher) {
            var page = localDataSource.getNowPlayingLoadedPage()
            while (page <= maxPageNumber) {
                when (val response = remoteMoviesDataSource.getNowPlayingMovies(page)) {
                    is Resource.Success -> {
                        localDataSource.updateCachedLocalNowPlayingMoviesList(
                            response.data?.movies ?: emptyList()
                        )
                        localDataSource.setNowPlayingLoadedPage(page)
                    }

                    else -> {
                        return@withContext Resource.Error(
                            errorCode = response.errorCode ?: "",
                            errorMessage = response.errorMessage
                        )
                    }
                }
                page++
            }
            return@withContext Resource.Success(
                localDataSource.getLocalNowPlayingMoviesList()
                    .map { it.toMoviesDomainModel() })
        }

    override suspend fun getPopularMovies(): Resource<List<MoviesDomainModel>> =
        withContext(ioDispatcher) {
            var page = localDataSource.getPopularLoadedPage()
            while (page <= maxPageNumber) {
                when (val response = remoteMoviesDataSource.getPopularMovies(page)) {
                    is Resource.Success -> {
                        localDataSource.updateCachedLocalPopularMoviesList(
                            response.data?.movies ?: emptyList()
                        )
                        localDataSource.setPopularLoadedPage(page)
                    }

                    else -> {
                        return@withContext Resource.Error(
                            errorCode = response.errorCode ?: "",
                            errorMessage = response.errorMessage
                        )
                    }
                }
                page++
            }
            return@withContext Resource.Success(
                localDataSource.getLocalPopularMoviesList().map { it.toMoviesDomainModel() })
        }

    override suspend fun getUpcomingMovies(): Resource<List<MoviesDomainModel>> =
        withContext(ioDispatcher) {
            var page = localDataSource.getUpcomingLoadedPage()
            while (page <= maxPageNumber) {
                when (val response = remoteMoviesDataSource.getUpcomingMovies(page)) {
                    is Resource.Success -> {
                        localDataSource.updateCachedLocalUpcomingMoviesList(
                            response.data?.movies ?: emptyList()
                        )
                        localDataSource.setUpcomingLoadedPage(page)
                    }

                    else -> {
                        return@withContext Resource.Error(
                            errorCode = response.errorCode ?: "",
                            errorMessage = response.errorMessage
                        )
                    }
                }
                page++
            }
            return@withContext Resource.Success(
                localDataSource.getLocalUpcomingMoviesList().map { it.toMoviesDomainModel() })
        }
}