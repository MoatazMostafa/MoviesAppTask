package com.example.moviesapptask.data.datasource.remote.movieDetails

import com.example.moviesapptask.data.datasource.dto.moviedetails.MovieDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsService {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailsDto
}