package com.example.moviesapptask.domain.model

import com.example.moviesapptask.data.datasource.dto.moives.MoviesDto
import com.example.moviesapptask.data.datasource.dto.moviedetails.MovieDetailsDto

fun MoviesDto.toMoviesDomainModel(): MoviesDomainModel =
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

fun MovieDetailsDto.toMovieDetailsDomainModel(): MovieDetailsDomainModel =
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
