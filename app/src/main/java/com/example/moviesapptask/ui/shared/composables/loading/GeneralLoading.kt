package com.example.moviesapptask.ui.shared.composables.loading

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme


@Composable
fun GeneralLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {},
        contentAlignment = Alignment.Center
    ) {
        FlashDotLoadingIndicator()
    }
}

@Preview
@Composable
private fun GeneralLoadingPreview() {
    MoviesAppTaskTheme {
        GeneralLoading()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun GeneralLoadingDarkModePreview() {
    MoviesAppTaskTheme {
        GeneralLoading()
    }
}