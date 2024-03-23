package com.example.moviesapptask.common.manager.navigation

import com.example.moviesapptask.common.manager.navigation.movieDetails.MovieDetailsNavDestination

object MoviesNavDestination {

    val Splash = SplashNavDestination

    val Home = HomeNavDestination

    val MovieDetails = MovieDetailsNavDestination
}



interface Route {
    val route: String
}
