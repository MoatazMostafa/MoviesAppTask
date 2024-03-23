package com.example.moviesapptask.common.koin

import com.example.moviesapptask.domain.usecases.movieDetails.MovieDetailsUseCase
import com.example.moviesapptask.domain.usecases.movies.MoviesUseCase
import com.example.moviesapptask.domain.usecases.movieDetails.MovieDetailsUseCaseImpl
import com.example.moviesapptask.domain.usecases.movies.MoviesUseCaseImpl
import org.koin.dsl.module

/**
 * This property is used to create UseCases and inject needed parameters
 * */
val useCaseModule = module {
    factory<MoviesUseCase> {
        MoviesUseCaseImpl(get())
    }
    factory<MovieDetailsUseCase> {
        MovieDetailsUseCaseImpl(get())
    }
}
