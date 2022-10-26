package com.android.diary.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun DiaryTheme3(content: @Composable () -> Unit) = MaterialTheme(
    colorScheme = DiaryColor3.colorScheme,
    shapes = DiaryShape3.shapes,
    typography = DiaryTypography3.typography,
    content = content
).also {
//    val systemUiController = rememberSystemUiController()
//    val primary = DiaryColor3.primary
//
//    LaunchedEffect(systemUiController) {
//        systemUiController.setStatusBarColor(primary)
//    }
}
