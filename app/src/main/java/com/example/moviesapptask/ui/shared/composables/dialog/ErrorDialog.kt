package com.example.moviesapptask.ui.shared.composables.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.moviesapptask.R
import com.example.moviesapptask.ui.shared.composables.ErrorMessage
import com.example.moviesapptask.ui.theme.MoviesAppTaskTheme


@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    error: ErrorMessage?,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    error?.let {
        AlertDialog(
            modifier = modifier.fillMaxWidth(),
            title = { Text(text = error.title) },
            text = { Text(text = error.message) },
            onDismissRequest = onDismiss,
            confirmButton = {
                DialogButton(
                    text = stringResource(id = R.string.ok),
                    onClick = onConfirm
                )
            }
        )
    }
}

@Preview
@Composable
private fun ErrorDialogPreview() {
    MoviesAppTaskTheme {
        ErrorDialog(
            error = ErrorMessage(title = "Error", message = "Unknown error."),
            onConfirm = {},
            onDismiss = {}
        )
    }
}