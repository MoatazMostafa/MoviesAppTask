package com.example.moviesapptask.data.datasource.remote.movies

import com.example.moviesapptask.data.datasource.dto.moives.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    suspend fun getDiscoverMoviesData(@Query("page") page: Int): MoviesResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesData(@Query("page") page: Int): MoviesResponse

    @GET("movie/popular")
    suspend fun getPopularMoviesData(@Query("page") page: Int): MoviesResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesData(@Query("page") page: Int): MoviesResponse
}