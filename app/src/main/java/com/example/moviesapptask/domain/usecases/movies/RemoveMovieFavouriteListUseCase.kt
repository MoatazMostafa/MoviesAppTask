package com.example.moviesapptask.domain.usecases.movies

import com.example.moviesapptask.ui.shared.uimodel.MoviesUIModel

interface RemoveMovieFavouriteListUseCase {
    suspend operator fun invoke(movie: MoviesUIModel)
}
