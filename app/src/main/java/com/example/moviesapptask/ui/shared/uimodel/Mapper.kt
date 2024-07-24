package com.example.moviesapptask.ui.shared.uimodel

import com.example.moviesapptask.domain.model.MovieDetailsDomainModel
import com.example.moviesapptask.domain.model.MoviesDomainModel

fun MoviesDomainModel.toMoviesUIModel(): MoviesUIModel =
    MoviesUIModel(
        adult,
        backdropPath,
        genreIds,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )

fun MovieDetailsDomainModel.toMovieDetailsUIModel(): MovieDetailsUIModel =
    MovieDetailsUIModel(
        adult,
        backdropPath,
        belongsToCollection,
        budget,
        genres,
        homepage,
        id,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        productionCompanies,
        productionCountries,
        releaseDate,
        revenue,
        runtime,
        spokenLanguages,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )

fun MoviesUIModel.toMoviesDomainModel(): MoviesDomainModel =
    MoviesDomainModel(
        adult,
        backdropPath,
        genreIds,
        id,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        releaseDate,
        title,
        video,
        voteAverage,
        voteCount
    )

fun MovieDetailsUIModel.toMovieDetailsDomainModel(): MovieDetailsDomainModel =
    MovieDetailsDomainModel(
        adult,
        backdropPath,
        belongsToCollection,
        budget,
        genres,
        homepage,
        id,
        imdbId,
        originalLanguage,
        originalTitle,
        overview,
        popularity,
        posterPath,
        productionCompanies,
        productionCountries,
        releaseDate,
        revenue,
        runtime,
        spokenLanguages,
        status,
        tagline,
        title,
        video,
        voteAverage,
        voteCount
    )