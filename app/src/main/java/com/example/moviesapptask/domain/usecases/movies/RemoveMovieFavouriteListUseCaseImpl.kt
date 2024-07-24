package com.example.moviesapptask.domain.usecases.movies

import com.example.moviesapptask.data.repository.movies.MoviesRepository
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import com.example.moviesapptask.ui.shared.uimodel.toMoviesDomainModel


class RemoveMovieFavouriteListUseCaseImpl(private val moviesRepository: MoviesRepository) : RemoveMovieFavouriteListUseCase {
    override suspend fun invoke(movie: MoviesUIModel) {
        moviesRepository.removeMovieFavouriteList(movie.toMoviesDomainModel())
    }
}
