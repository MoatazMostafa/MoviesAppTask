package com.example.moviesapptask.ui.shared.base

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.moviesapptask.ui.shared.composables.dialog.ErrorDialog

@Composable
fun BaseScreen(
    content: @Composable () -> Unit,
    viewModel: BaseViewModel,
    isBackPressAllowed: Boolean = true
) {
    content()
    ErrorDialog(
        error = viewModel.generalError.collectAsState().value,
        onConfirm = { viewModel.resetGeneralError() },
        onDismiss = {}
    )
    BackHandler(enabled = !isBackPressAllowed) {
    }
}
