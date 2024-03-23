package com.example.moviesapptask.ui.features.movieDetails.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.moviesapptask.ui.shared.composables.BackIconButton
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme

@Composable
fun MovieDetailsToolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .wrapContentHeight()
            .background(
                Brush.verticalGradient(
                    0F to Color.Black.copy(alpha = 1.0F),
                    1F to Color.Black.copy(alpha = 0.0F),
                )
            )
    ) {
        val (backBtn, closeBtn) = createRefs()
        BackIconButton(
            modifier = Modifier
                .size(45.dp)
                .constrainAs(backBtn) {
                    start.linkTo(parent.start)
                    linkTo(
                        top = closeBtn.top,
                        bottom = closeBtn.bottom,
                        topMargin = 0.dp,
                        bias = 0.5F
                    )
                },
            onClick = onBackClick
        )
    }
}

@Preview(heightDp = 50, widthDp = 400)
@Composable
private fun NewsToolbarPreview() {
   MoviesAppTaskTheme {
        MovieDetailsToolbar(
            modifier = Modifier
        ) {}
    }
}