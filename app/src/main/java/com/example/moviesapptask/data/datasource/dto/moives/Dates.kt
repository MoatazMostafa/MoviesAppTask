package com.example.moviesapptask.data.datasource.dto.moives


import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("maximum") val maximum: String?,
    @SerializedName("minimum") val minimum: String?
)