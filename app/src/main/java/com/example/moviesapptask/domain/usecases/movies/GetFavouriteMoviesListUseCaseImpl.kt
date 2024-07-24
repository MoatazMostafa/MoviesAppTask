package com.example.moviesapptask.domain.usecases.movies

import com.example.moviesapptask.data.repository.movies.MoviesRepository
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import com.example.moviesapptask.ui.shared.uimodel.toMoviesDomainModel
import com.example.moviesapptask.ui.shared.uimodel.toMoviesUIModel


class GetFavouriteMoviesListUseCaseImpl(private val moviesRepository: MoviesRepository) :
    GetFavouriteMoviesListUseCase {
    override suspend fun invoke(): List<MoviesUIModel> =
        moviesRepository.getFavouriteMoviesList().map { it.toMoviesUIModel() }
}
