package com.example.moviesapptask.ui.shared.uimodel

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class ToolbarProperties(
    val showToolbar: Boolean = true,
    val title: String? = null,
    val showCenteredLogo: Boolean = title.isNullOrEmpty(),
    val backgroundColor: Color = Color.White,
    val backNavigationAction: (() -> Unit)? = null,
    val actions: @Composable RowScope.() -> Unit = {},
)