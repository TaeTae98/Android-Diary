package com.android.diary.ui.uistate.memo

import androidx.compose.runtime.Immutable

@Immutable
sealed class MemoListUiState {
    data class List(
        val onAdd: () -> Unit = {}
    ) : MemoListUiState()
}