package com.example.moviesapptask.data.datasource.remote.movies

import com.example.moviesapptask.common.network.ServerRequest
import com.example.moviesapptask.common.network.configuration.ConfigurationService
import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.datasource.dto.moives.MoviesResponse
import retrofit2.HttpException

class MoviesDataSourceImpl(configService: ConfigurationService) :
    ServerRequest(configService.tmdbBaseUrl, configService.accessToken),
    MoviesDataSource {

    private val backendService by lazy { retrofit.create(MoviesService::class.java) }

    override suspend fun getDiscoverMovies(page: Int): Resource<MoviesResponse> =
        try {
            Resource.Success(backendService.getDiscoverMoviesData(page))
        } catch (e: HttpException) {
            Resource.Error(e.code().toString() , e.message())
        }

    override suspend fun getNowPlayingMovies(page: Int): Resource<MoviesResponse> =
        try {
            Resource.Success(backendService.getNowPlayingMoviesData(page))
        } catch (e: HttpException) {
            Resource.Error(e.code().toString() , e.message())
        }

    override suspend fun getPopularMovies(page: Int): Resource<MoviesResponse> =
        try {
            Resource.Success(backendService.getPopularMoviesData(page))
        } catch (e: HttpException) {
            Resource.Error(e.code().toString() , e.message())
        }

    override suspend fun getUpcomingMovies(page: Int): Resource<MoviesResponse> =
        try {
            Resource.Success(backendService.getUpcomingMoviesData(page))
        } catch (e: HttpException) {
            Resource.Error(e.code().toString() , e.message())
        }
}