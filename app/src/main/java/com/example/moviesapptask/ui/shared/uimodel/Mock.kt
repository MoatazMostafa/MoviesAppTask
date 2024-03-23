package com.example.moviesapptask.ui.shared.uimodel

import com.example.moviesapptask.data.datasource.dto.moviedetails.Genre
import com.example.moviesapptask.data.datasource.dto.moviedetails.ProductionCompany
import com.example.moviesapptask.data.datasource.dto.moviedetails.ProductionCountry
import com.example.moviesapptask.data.datasource.dto.moviedetails.SpokenLanguage

fun moviesListItemMock():MoviesUIModel =
    MoviesUIModel(
        id = 1096197,
        posterPath =  "/7FpGJTN8IL6IBvQMp6YHBFyhO9Z.jpg",
        adult = false,
        backdropPath = "/4woSOUD0equAYzvwhWBHIJDCM88.jpg",
        genreIds = listOf(28, 27, 53),
        originalLanguage = "en",
        originalTitle = "No Way Up",
        overview =  "Characters from different backgrounds are thrown together when the plane they're travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides.",
        popularity = 2426.543,
        releaseDate = "2024-01-18",
        title =  "No Way Up",
        video = false,
        voteAverage =  5.794,
        voteCount = 80
    )

fun movieDetailsMock():MovieDetailsUIModel =
    MovieDetailsUIModel(
        adult = false,
        backdropPath = "/nJ0jz1J3zNja31UEMeklhgFeMw5.jpg",
        belongsToCollection = null,
        budget = 16000000,
        genres = listOf(
            Genre(
                id = 53,
                name = "Thriller"
            ),
            Genre(
                id = 878,
                name = "Science Fiction"
            ),
        ),
        homepage = null,

        imdbId = "1",
        originalLanguage = "en",
        originalTitle = "The Thirteenth Floor",
        overview = "Los Angeles. A wealthy man, known as Mr. Fuller, discovers a shocking secret about the world he lives in. Fearing for his life, he leaves a desperate message for a friend of his in the most unexpected place.",
        popularity = 32.176,
        posterPath = "/7oaie3ZBc9UuWZLF24crro1pone.jpg",
        productionCompanies = listOf(
            ProductionCompany(
                id = 620,
                logoPath = null,
                name = "Centropolis Film Productions",
                originCountry = ""
            ),
            ProductionCompany(
                id = 5,
                logoPath = null,
                name = "Columbia Pictures",
                originCountry = "US"
            )
        ),
        productionCountries = listOf(
            ProductionCountry(
                iso31661 = "DE",
                name = "Germany"
            ),
            ProductionCountry(
                iso31661 = "US",
                name = "United States of America"
            )
        ),
        releaseDate = "1999-04-16",
        revenue = 18600000,
        runtime = 101,
        spokenLanguages = listOf(
            SpokenLanguage(
                englishName = "English",
                iso6391 = "en",
                name = "English"
            )
        ),
        status = "Released",
        title = "The Thirteenth Floor",
        voteAverage = 7.032,
        voteCount = 1219,
        tagline = "Question reality.",
        video = false,
        id = 1090
    )
