package com.android.diary.ui.uistate.core

data class TextInputUiState(
    val value: String = "",
    val onValueChange: (String) -> Unit = {},
    val errorAt: Long? = null
)
