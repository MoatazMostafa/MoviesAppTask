package com.example.moviesapptask.data.datasource.remote.movieDetails


import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.datasource.dto.moviedetails.MovieDetailsDto


interface MovieDetailsDataSource {
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsDto>
}