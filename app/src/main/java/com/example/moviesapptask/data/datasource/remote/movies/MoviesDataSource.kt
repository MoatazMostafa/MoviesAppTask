package com.example.moviesapptask.data.datasource.remote.movies


import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.datasource.dto.moives.MoviesResponse


interface MoviesDataSource {
    suspend fun getDiscoverMovies(page: Int): Resource<MoviesResponse>
    suspend fun getNowPlayingMovies(page: Int): Resource<MoviesResponse>
    suspend fun getPopularMovies(page: Int): Resource<MoviesResponse>
    suspend fun getUpcomingMovies(page: Int): Resource<MoviesResponse>
}