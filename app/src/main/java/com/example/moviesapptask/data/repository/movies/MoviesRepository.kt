package com.example.moviesapptask.data.repository.movies

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.domain.model.MoviesDomainModel
import com.example.moviesapptask.domain.model.MoviesListType


interface MoviesRepository {
    suspend fun addMovieFavouriteList(movie: MoviesDomainModel)
    suspend fun removeMovieFavouriteList(movie: MoviesDomainModel)
    suspend fun getFavouriteMoviesList(): List<MoviesDomainModel>
    suspend fun getMoviesList(moviesListType: MoviesListType): Resource<List<MoviesDomainModel>>
    suspend fun getNowPlayingMovies(): Resource<List<MoviesDomainModel>>
    suspend fun getDiscoverMovies(): Resource<List<MoviesDomainModel>>
    suspend fun getPopularMovies(): Resource<List<MoviesDomainModel>>
    suspend fun getUpcomingMovies(): Resource<List<MoviesDomainModel>>
}