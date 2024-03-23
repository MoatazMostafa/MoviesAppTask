package com.example.moviesapptask.data.datasource.local

import com.example.moviesapptask.common.helper.DateTimeProvider
import com.example.moviesapptask.data.cache.timedMemoryCache
import com.example.moviesapptask.data.datasource.dto.moives.MoviesDto
import com.example.moviesapptask.data.datasource.dto.moviedetails.MovieDetailsDto
import kotlinx.coroutines.runBlocking
import java.time.Duration

class LocalDataSourceImpl(dateTimeProvider: DateTimeProvider) :
    LocalDataSource {

    private val discoverMoviesCache =
        timedMemoryCache<String, ArrayList<MoviesDto>>(dateTimeProvider, Duration.ofMinutes(30))
    private val nowPlayingMoviesCache =
        timedMemoryCache<String, ArrayList<MoviesDto>>(dateTimeProvider, Duration.ofMinutes(30))
    private val popularMoviesCache =
        timedMemoryCache<String, ArrayList<MoviesDto>>(dateTimeProvider, Duration.ofMinutes(30))
    private val upcomingMoviesCache =
        timedMemoryCache<String, ArrayList<MoviesDto>>(dateTimeProvider, Duration.ofMinutes(30))

    private var discoverLoadedPage =
        timedMemoryCache<String, Int>(dateTimeProvider, Duration.ofMinutes(30))
    private var nowPlayingLoadedPage =
        timedMemoryCache<String, Int>(dateTimeProvider, Duration.ofMinutes(30))
    private var popularLoadedPage =
        timedMemoryCache<String, Int>(dateTimeProvider, Duration.ofMinutes(30))
    private var upcomingLoadedPage =
        timedMemoryCache<String, Int>(dateTimeProvider, Duration.ofMinutes(30))

    private val discoverMoviesCachingKey = "DISCOVER_CACHING_KEY"
    private val nowPlayingMoviesCachingKey = "NOW_PLAYING_CACHING_KEY"
    private val popularMoviesCachingKey = "POPULAR_CACHING_KEY"
    private val upcomingMoviesCachingKey = "UPCOMING_CACHING_KEY"

    private var discoverLoadedPageCachingKey = "DISCOVER_LOADED_PAGE_CACHING_KEY"
    private var nowPlayingLoadedPageCachingKey = "NOW_PLAYING_LOADED_PAGE_CACHING_KEY"
    private var popularLoadedPageCachingKey = "POPULAR_LOADED_PAGE_CACHING_KEY"
    private var upcomingLoadedCachingKey = "UPCOMING_LOADED_PAGE_CACHING_KEY"


    private var movieDetails =
        timedMemoryCache<String, MovieDetailsDto>(dateTimeProvider, Duration.ofMinutes(30))

    private val movieDetailsCachingKey = "MOVIE_DETAILS_CACHING_KEY"


    override fun getLocalDiscoverMoviesList(): ArrayList<MoviesDto> = runBlocking {
        discoverMoviesCache.getEntry(discoverMoviesCachingKey) ?: arrayListOf()
    }

    override fun updateCachedLocalDiscoverMoviesList(moviesList: List<MoviesDto>) {
        val cachedList = getLocalNowPlayingMoviesList()
        cachedList.addAll(moviesList)
        runBlocking { discoverMoviesCache.putEntry(discoverMoviesCachingKey, cachedList) }
    }

    override fun clearLocalDiscoverMoviesList() {
        runBlocking { discoverMoviesCache.removeAll() }
    }

    override fun getDiscoverLoadedPage(): Int = runBlocking {
        discoverLoadedPage.getEntry(discoverLoadedPageCachingKey) ?: 1
    }

    override fun setDiscoverLoadedPage(page: Int) {
        runBlocking { discoverLoadedPage.putEntry(discoverLoadedPageCachingKey, page) }
    }

    override fun getLocalNowPlayingMoviesList(): ArrayList<MoviesDto> = runBlocking {
        nowPlayingMoviesCache.getEntry(nowPlayingMoviesCachingKey) ?: arrayListOf()
    }

    override fun updateCachedLocalNowPlayingMoviesList(moviesList: List<MoviesDto>) {
        val cachedList = getLocalNowPlayingMoviesList()
        cachedList.addAll(moviesList)
        runBlocking { nowPlayingMoviesCache.putEntry(nowPlayingMoviesCachingKey, cachedList) }
    }

    override fun clearLocalNowPlayingMoviesList() {
        runBlocking { nowPlayingMoviesCache.removeAll() }
    }

    override fun getNowPlayingLoadedPage(): Int = runBlocking {
        nowPlayingLoadedPage.getEntry(nowPlayingLoadedPageCachingKey) ?: 1
    }

    override fun setNowPlayingLoadedPage(page: Int) {
        runBlocking { nowPlayingLoadedPage.putEntry(nowPlayingLoadedPageCachingKey, page) }
    }

    override fun getLocalPopularMoviesList(): ArrayList<MoviesDto> = runBlocking {
        popularMoviesCache.getEntry(popularMoviesCachingKey) ?: arrayListOf()
    }

    override fun updateCachedLocalPopularMoviesList(moviesList: List<MoviesDto>) {
        val cachedList = getLocalPopularMoviesList()
        cachedList.addAll(moviesList)
        runBlocking { popularMoviesCache.putEntry(popularMoviesCachingKey, cachedList) }
    }

    override fun clearLocalPopularMoviesList() {
        runBlocking { popularMoviesCache.removeAll() }
    }

    override fun getPopularLoadedPage(): Int = runBlocking {
        popularLoadedPage.getEntry(popularLoadedPageCachingKey) ?: 1
    }

    override fun setPopularLoadedPage(page: Int) {
        runBlocking { popularLoadedPage.putEntry(popularLoadedPageCachingKey, page) }
    }

    override fun getLocalUpcomingMoviesList(): ArrayList<MoviesDto> {
        return runBlocking {
            upcomingMoviesCache.getEntry(upcomingMoviesCachingKey) ?: arrayListOf()
        }
    }

    override fun updateCachedLocalUpcomingMoviesList(moviesList: List<MoviesDto>) {
        val cachedList = getLocalUpcomingMoviesList()
        cachedList.addAll(moviesList)
        runBlocking { upcomingMoviesCache.putEntry(upcomingMoviesCachingKey, cachedList) }
    }

    override fun clearLocalUpcomingMoviesList() {
        runBlocking { upcomingMoviesCache.removeAll() }
    }

    override fun getUpcomingLoadedPage(): Int = runBlocking {
        upcomingLoadedPage.getEntry(upcomingLoadedCachingKey) ?: 1
    }

    override fun setUpcomingLoadedPage(page: Int) {
        runBlocking { upcomingLoadedPage.putEntry(upcomingLoadedCachingKey, page) }
    }

    override fun getLocalMovieDetails(): MovieDetailsDto? = runBlocking {
        movieDetails.getEntry(movieDetailsCachingKey)
    }

    override fun cacheLocalMovieDetails(movieDetailsDto: MovieDetailsDto) {
        runBlocking { movieDetails.putEntry(movieDetailsCachingKey, movieDetailsDto) }
    }

    override fun clearLocalMovieDetails() {
        runBlocking { movieDetails.removeAll() }
    }
}
