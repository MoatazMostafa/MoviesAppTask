package com.example.moviesapptask.common.network.configuration

import android.content.Context
import com.example.moviesapptask.BuildConfig
import com.example.moviesapptask.R

class ConfigurationServiceImpl (private val context: Context) :
    ConfigurationService {
    override val tmdbBaseUrl: String
        get() = context.getString(R.string.tmdb_base_url)
    override val accessToken: String
        get() = BuildConfig.TMDB_API_KEY
}