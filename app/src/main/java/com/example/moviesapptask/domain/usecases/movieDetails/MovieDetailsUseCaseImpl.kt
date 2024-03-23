package com.example.moviesapptask.domain.usecases.movieDetails

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.repository.movieDetails.MovieDetailsRepository
import com.example.moviesapptask.domain.model.MovieDetailsDomainModel

class MovieDetailsUseCaseImpl(private val movieDetailsRepository: MovieDetailsRepository) : MovieDetailsUseCase {
    override suspend fun invoke(movieId: Int): Resource<MovieDetailsDomainModel> =
        movieDetailsRepository.getMovieDetails(movieId)
}
