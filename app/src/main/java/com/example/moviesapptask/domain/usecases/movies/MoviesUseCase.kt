package com.example.moviesapptask.domain.usecases.movies

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.domain.model.MoviesDomainModel
import com.example.moviesapptask.domain.model.MoviesListType

interface MoviesUseCase {
    suspend operator fun invoke(moviesListType: MoviesListType): Resource<List<MoviesDomainModel>>
}
