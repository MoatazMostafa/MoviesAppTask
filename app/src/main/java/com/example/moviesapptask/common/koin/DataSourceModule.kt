package com.example.moviesapptask.common.koin


import com.example.moviesapptask.data.datasource.local.LocalDataSource
import com.example.moviesapptask.data.datasource.local.LocalDataSourceImpl
import com.example.moviesapptask.data.datasource.remote.movieDetails.MovieDetailsDataSource
import com.example.moviesapptask.data.datasource.remote.movieDetails.MovieDetailsDataSourceImpl
import com.example.moviesapptask.data.datasource.remote.movies.MoviesDataSource
import com.example.moviesapptask.data.datasource.remote.movies.MoviesDataSourceImpl
import org.koin.dsl.module

/**
 * This property is used to create Repositories and inject needed parameters
 * */
val dataSourceModule = module {

    single<MoviesDataSource> {
        MoviesDataSourceImpl(configService = get())
    }

    single<MovieDetailsDataSource> {
        MovieDetailsDataSourceImpl(configService = get())
    }

    single<LocalDataSource> {
        LocalDataSourceImpl(dateTimeProvider = get(), context = get())
    }
}
