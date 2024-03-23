package com.example.moviesapptask.common.koin


import com.example.moviesapptask.data.repository.movieDetails.MovieDetailsRepository
import com.example.moviesapptask.data.repository.movieDetails.MovieDetailsRepositoryImpl
import com.example.moviesapptask.data.repository.movies.MoviesRepositoryImpl
import com.example.moviesapptask.data.repository.movies.MoviesRepository
import org.koin.dsl.module

/**
 * This property is used to create Repositories and inject needed parameters
 * */
val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(
            remoteMoviesDataSource = get(),
            localDataSource = get()
        )
    }

    single<MovieDetailsRepository> {
        MovieDetailsRepositoryImpl(
            remoteMovieDetailsDataSource = get(),
            localDataSource = get()
        )
    }
}