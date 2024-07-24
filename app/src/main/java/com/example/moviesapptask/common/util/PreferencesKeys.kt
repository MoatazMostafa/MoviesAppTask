package com.example.moviesapptask.common.util

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    const val PREFERENCES_NAME = "MoviesAppPreferences"
    val FAVOURITE_MOVIES_LIST = stringPreferencesKey("favourite_movies_list")
}