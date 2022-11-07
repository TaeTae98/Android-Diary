package com.android.diary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

object DiaryColor3 {
    private val lightColorScheme = lightColorScheme()

    private val darkColorScheme = darkColorScheme()

    val colorScheme: ColorScheme
        @Composable
        get() = if (isSystemInDarkTheme()) {
            darkColorScheme
        } else {
            lightColorScheme
        }
}
