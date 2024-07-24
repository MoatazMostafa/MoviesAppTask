package com.example.moviesapptask.domain.usecases.movies

import com.example.moviesapptask.data.repository.movies.MoviesRepository
import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel
import com.example.moviesapptask.ui.shared.uimodel.toMoviesDomainModel


class AddMovieFavouriteListUseCaseImpl(private val moviesRepository: MoviesRepository) : AddMovieFavouriteListUseCase {
    override suspend fun invoke(movie: MoviesUIModel) {
        moviesRepository.addMovieFavouriteList(movie.toMoviesDomainModel())
    }
}
