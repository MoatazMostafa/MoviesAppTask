package com.example.moviesapptask.data.repository.movieDetails

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.domain.model.MovieDetailsDomainModel


interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId:Int): Resource<MovieDetailsDomainModel>
}