package com.example.moviesapptask.domain.usecases.movieDetails

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.domain.model.MovieDetailsDomainModel

interface MovieDetailsUseCase {
    suspend operator fun invoke(movieId: Int): Resource<MovieDetailsDomainModel>
}
