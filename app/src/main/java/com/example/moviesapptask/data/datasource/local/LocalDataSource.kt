package com.example.moviesapptask.data.datasource.local

import com.example.moviesapptask.data.datasource.dto.moives.MoviesDto
import com.example.moviesapptask.data.datasource.dto.moviedetails.MovieDetailsDto


interface LocalDataSource {

    suspend fun addMovieFavouriteList(movie: MoviesDto)
    suspend fun removeMovieFavouriteList(movie: MoviesDto)
    suspend fun getFavouriteMoviesList(): List<MoviesDto>

    fun getLocalDiscoverMoviesList(): ArrayList<MoviesDto>
    fun updateCachedLocalDiscoverMoviesList(moviesList: List<MoviesDto>)
    fun clearLocalDiscoverMoviesList()
    fun getDiscoverLoadedPage(): Int
    fun setDiscoverLoadedPage(page: Int)

    fun getLocalNowPlayingMoviesList(): ArrayList<MoviesDto>
    fun updateCachedLocalNowPlayingMoviesList(moviesList: List<MoviesDto>)
    fun clearLocalNowPlayingMoviesList()
    fun getNowPlayingLoadedPage(): Int
    fun setNowPlayingLoadedPage(page: Int)

    fun getLocalPopularMoviesList(): ArrayList<MoviesDto>
    fun updateCachedLocalPopularMoviesList(moviesList: List<MoviesDto>)
    fun clearLocalPopularMoviesList()
    fun getPopularLoadedPage(): Int
    fun setPopularLoadedPage(page: Int)

    fun getLocalUpcomingMoviesList(): ArrayList<MoviesDto>
    fun updateCachedLocalUpcomingMoviesList(moviesList: List<MoviesDto>)
    fun clearLocalUpcomingMoviesList()
    fun getUpcomingLoadedPage(): Int
    fun setUpcomingLoadedPage(page: Int)

    fun getLocalMovieDetails(): MovieDetailsDto?
    fun cacheLocalMovieDetails(movieDetailsDto: MovieDetailsDto)
    fun clearLocalMovieDetails()
}
