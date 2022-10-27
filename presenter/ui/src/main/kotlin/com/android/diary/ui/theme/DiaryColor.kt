package com.android.diary.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object DiaryColor {
    val lightPrimary = Color(0xFF5D70BD)
    val lightSurface = Color(0xFFFFFFFF)
    private val lightColors = lightColors(
        primary = lightPrimary,
        surface = lightSurface
    )

    val primary: Color
        @Composable
        get() = lightPrimary

    val surface: Color
        @Composable
        get() = lightSurface

    val colors: Colors
        @Composable
        get() = lightColors
}