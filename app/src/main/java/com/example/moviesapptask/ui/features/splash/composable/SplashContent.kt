package com.example.moviesapptask.ui.features.splash.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviesapptask.R
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme
import com.example.moviesapptask.ui.theme.Purple80


@Composable
fun SplashContent(
    modifier: Modifier,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    var state by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true, block = {
        onStart()
        state = true
    })

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            onStop()
        }
    })

    Box(modifier) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Purple80)
        ) {
        }
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize(),
            visible = state,
            enter = fadeIn() + slideInVertically(initialOffsetY = { 200 }),
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize(),
            ) {

                val (logo) = createRefs()

                Box(
                    modifier = Modifier
                        .size(170.dp)
                        .constrainAs(logo) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 2.dp, vertical = 3.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_movie_logo),
                        contentDescription = null,
                    )

                }

            }
        }

    }
}

@Preview
@Composable
fun SplashContentPreview() {
    MoviesAppTaskTheme {
        SplashContent(Modifier.fillMaxSize(), {}, {})
    }
}