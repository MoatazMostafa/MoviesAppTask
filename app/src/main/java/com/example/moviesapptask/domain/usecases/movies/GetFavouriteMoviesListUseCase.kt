package com.example.moviesapptask.domain.usecases.movies

import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel

interface GetFavouriteMoviesListUseCase {
    suspend operator fun invoke(): List<MoviesUIModel>
}
