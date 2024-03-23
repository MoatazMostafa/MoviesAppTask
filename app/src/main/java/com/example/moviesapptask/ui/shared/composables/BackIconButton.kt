package com.example.moviesapptask.ui.shared.composables

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapptask.R
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme


@Composable
fun BackIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BackIconButtonLightModePreview() {
    MoviesAppTaskTheme {
        BackIconButton {}
    }
}
