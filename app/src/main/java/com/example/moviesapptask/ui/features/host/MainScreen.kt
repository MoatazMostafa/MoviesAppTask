package com.example.moviesapptask.ui.features.host

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesapptask.common.manager.navigation.MoviesNavDestination
import com.example.moviesapptask.ui.features.home.HomeScreen
import com.example.moviesapptask.ui.features.home.HomeViewModel
import com.example.moviesapptask.ui.features.movieDetails.MovieDetailsScreen
import com.example.moviesapptask.ui.features.movieDetails.MovieDetailsViewModel
import com.example.moviesapptask.ui.features.splash.SplashScreen
import com.example.moviesapptask.ui.features.splash.SplashViewModel
import com.example.moviesapptask.ui.shared.uimodel.ToolbarProperties
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController =  rememberNavController(),
) {
    var toolbarProperties by remember { mutableStateOf(ToolbarProperties(showToolbar = false)) }

    Scaffold(
        modifier = modifier.navigationBarsPadding()) { innerPadding ->
        Column(modifier = modifier.zIndex(100f)) {
            NavHost(
                navController = navController,
                startDestination = MoviesNavDestination.Splash.navComposableDestination
            ) {
                composable(route = MoviesNavDestination.Splash.navComposableDestination) {
                    toolbarProperties = ToolbarProperties(
                        showToolbar = false,
                    )
                    val splashViewModel = koinViewModel<SplashViewModel>().apply {
                        updateNavController(navController = navController)
                        parametersOf(navController)
                    }

                    SplashScreen(
                        modifier = paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = false,
                            showBottomBar = false,
                            shouldRenderUnderStatusBar = true
                        ),
                        splashViewModel = splashViewModel
                    )
                }
                composable(route = MoviesNavDestination.Home.navComposableDestination) {
                    toolbarProperties = ToolbarProperties(
                        showToolbar = false,
                    )
                    val homeViewModel = koinViewModel<HomeViewModel>().apply {
                        updateNavController(navController = navController)
                        parametersOf(navController)
                    }

                    HomeScreen(
                        modifier = paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = false,
                            showBottomBar = false,
                            shouldRenderUnderStatusBar = true
                        ),
                        homeViewModel = homeViewModel
                    )
                }
                composable(route = MoviesNavDestination.MovieDetails.navComposableDestination)
                { backStackEntry ->
                    toolbarProperties = ToolbarProperties(
                        showToolbar = true,
                    )

                    val movieDetailsViewModel = koinViewModel<MovieDetailsViewModel> {
                        parametersOf(backStackEntry.arguments)
                    }.apply {
                        updateNavController(navController = navController)
                        parametersOf(navController)
                    }


                    MovieDetailsScreen(
                        modifier = paddedModifier(
                            innerPaddingValues = innerPadding,
                            showToolbar = false,
                            showBottomBar = false,
                            shouldRenderUnderStatusBar = true
                        ),
                        movieDetailsViewModel = movieDetailsViewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun paddedModifier(
    innerPaddingValues: PaddingValues,
    showToolbar: Boolean,
    showBottomBar: Boolean,
    shouldRenderUnderStatusBar: Boolean = false
): Modifier {

    val padding = PaddingValues(
        start = innerPaddingValues.calculateStartPadding(LayoutDirection.Ltr),
        end = innerPaddingValues.calculateEndPadding(LayoutDirection.Ltr),
        bottom = if (showBottomBar) innerPaddingValues.calculateBottomPadding() else 0.dp,
        top = if (showToolbar) innerPaddingValues.calculateTopPadding() else 0.dp,
    )

    var modifier = Modifier
        .padding(padding)

    if (!showToolbar && !shouldRenderUnderStatusBar) modifier = modifier.statusBarsPadding()

    return modifier.fillMaxSize()
}