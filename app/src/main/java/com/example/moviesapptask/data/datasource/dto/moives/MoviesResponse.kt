package com.example.moviesapptask.data.datasource.dto.moives


import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("dates") val dates: Dates?,
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val movies: List<MoviesDto>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)