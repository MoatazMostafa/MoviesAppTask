package com.example.moviesapptask.data.datasource.remote.movieDetails

import com.example.moviesapptask.common.network.ServerRequest
import com.example.moviesapptask.common.network.configuration.ConfigurationService
import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.datasource.dto.moviedetails.MovieDetailsDto
import retrofit2.HttpException

class MovieDetailsDataSourceImpl(configService: ConfigurationService) :
    ServerRequest(configService.tmdbBaseUrl, configService.accessToken),
    MovieDetailsDataSource {

    private val backendService by lazy { retrofit.create(MovieDetailsService::class.java) }
    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsDto> =
        try {
            Resource.Success(backendService.getMovieDetails(movieId))
        } catch (e: HttpException) {
            Resource.Error(e.code().toString(), e.message())
        }
}