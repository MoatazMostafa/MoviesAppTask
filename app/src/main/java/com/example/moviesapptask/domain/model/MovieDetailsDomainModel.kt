package com.example.moviesapptask.domain.model

import com.example.moviesapptask.data.datasource.dto.moviedetails.Genre
import com.example.moviesapptask.data.datasource.dto.moviedetails.ProductionCompany
import com.example.moviesapptask.data.datasource.dto.moviedetails.ProductionCountry
import com.example.moviesapptask.data.datasource.dto.moviedetails.SpokenLanguage

data class MovieDetailsDomainModel(
    val adult: Boolean?,
    val backdropPath: String?,
    val belongsToCollection: Any?,
    val budget: Int?,
    val genres: List<Genre?>?,
    val homepage: String?,
    val id: Int?,
    val imdbId: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val productionCompanies: List<ProductionCompany?>?,
    val productionCountries: List<ProductionCountry?>?,
    val releaseDate: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguage?>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)