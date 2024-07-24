package com.example.moviesapptask.common.koin

import com.example.moviesapptask.domain.usecases.movieDetails.MovieDetailsUseCase
import com.example.moviesapptask.domain.usecases.movies.MoviesUseCase
import com.example.moviesapptask.domain.usecases.movieDetails.MovieDetailsUseCaseImpl
import com.example.moviesapptask.domain.usecases.movies.AddMovieFavouriteListUseCase
import com.example.moviesapptask.domain.usecases.movies.AddMovieFavouriteListUseCaseImpl
import com.example.moviesapptask.domain.usecases.movies.GetFavouriteMoviesListUseCase
import com.example.moviesapptask.domain.usecases.movies.GetFavouriteMoviesListUseCaseImpl
import com.example.moviesapptask.domain.usecases.movies.MoviesUseCaseImpl
import com.example.moviesapptask.domain.usecases.movies.RemoveMovieFavouriteListUseCase
import com.example.moviesapptask.domain.usecases.movies.RemoveMovieFavouriteListUseCaseImpl
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
    factory<AddMovieFavouriteListUseCase> {
        AddMovieFavouriteListUseCaseImpl(get())
    }
    factory<RemoveMovieFavouriteListUseCase> {
        RemoveMovieFavouriteListUseCaseImpl(get())
    }
    factory<GetFavouriteMoviesListUseCase> {
        GetFavouriteMoviesListUseCaseImpl(get())
    }
}