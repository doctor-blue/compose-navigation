package com.devcomentry.composenavigation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.devcomentry.composenavigation.ui.theme.Typography

@Composable
fun DemoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = Typography,
        content = content
    )
}