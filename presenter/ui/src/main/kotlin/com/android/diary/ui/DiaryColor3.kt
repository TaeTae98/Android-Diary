package com.android.diary.ui

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object DiaryColor3 {
    private val lightColorScheme = lightColorScheme(
        primary = DiaryColor.lightPrimary,
        surface = DiaryColor.lightSurface
    )

    val primary: Color
        @Composable
        get() = DiaryColor.primary

    val surface: Color
        @Composable
        get() = DiaryColor.surface

    val colorScheme: ColorScheme
        @Composable
        get() = lightColorScheme
}