package com.example.moviesapptask.ui.features.host

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModel()
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTaskTheme {
                navController = rememberNavController().also { _navController ->
                    viewModel.updateNavController(_navController)
                    Box(modifier = Modifier.fillMaxSize()) {

                        MainScreen(
                            navController = _navController,
                        )
                    }
                }
            }
        }
    }
}