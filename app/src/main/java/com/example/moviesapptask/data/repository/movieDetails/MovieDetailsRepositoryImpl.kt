package com.example.moviesapptask.data.repository.movieDetails

import com.example.moviesapptask.common.util.Resource
import com.example.moviesapptask.data.datasource.local.LocalDataSource
import com.example.moviesapptask.data.datasource.remote.movieDetails.MovieDetailsDataSource
import com.example.moviesapptask.domain.model.MovieDetailsDomainModel
import com.example.moviesapptask.domain.model.toMovieDetailsDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailsRepositoryImpl(
    private val remoteMovieDetailsDataSource: MovieDetailsDataSource,
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): Resource<MovieDetailsDomainModel> =
        withContext(ioDispatcher) {
            val localMovieDetails = localDataSource.getLocalMovieDetails()
            if(localMovieDetails!= null && localMovieDetails.id == movieId){
                return@withContext Resource.Success(localMovieDetails.toMovieDetailsDomainModel())
            }else {
                when (val response = remoteMovieDetailsDataSource.getMovieDetails(movieId)) {
                    is Resource.Success -> {
                        return@withContext Resource.Success(response.data?.toMovieDetailsDomainModel())
                    }
                    else -> {
                        return@withContext Resource.Error(
                            errorCode = response.errorCode ?: "",
                            errorMessage = response.errorMessage
                        )
                    }
                }
            }
        }
}