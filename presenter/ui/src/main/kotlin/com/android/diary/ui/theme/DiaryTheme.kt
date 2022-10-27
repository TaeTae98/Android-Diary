package com.android.diary.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DiaryTheme(content: @Composable () -> Unit) = MaterialTheme(
    colors = DiaryColor.colors,
    shapes = DiaryShape.shapes,
    typography = DiaryTypography.typography,
    content = content
).also {
    val systemUiController = rememberSystemUiController()
    val primary = DiaryColor3.primary

    LaunchedEffect(systemUiController) {
        systemUiController.setStatusBarColor(primary)
    }
}
