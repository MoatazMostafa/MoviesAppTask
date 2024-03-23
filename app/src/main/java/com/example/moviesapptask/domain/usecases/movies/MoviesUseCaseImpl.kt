package com.example.moviesapptask.domain.usecases.movies

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.repository.movies.MoviesRepository
import com.example.moviesapptask.domain.model.MoviesDomainModel
import com.example.moviesapptask.domain.model.MoviesListType


class MoviesUseCaseImpl(private val moviesRepository: MoviesRepository) : MoviesUseCase {

    override suspend fun invoke(moviesListType: MoviesListType): Resource<List<MoviesDomainModel>> =
        moviesRepository.getMoviesList(moviesListType)
}
