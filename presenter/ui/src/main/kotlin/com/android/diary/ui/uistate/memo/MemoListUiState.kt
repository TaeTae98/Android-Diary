package com.android.diary.ui.uistate.memo

sealed interface MemoListUiState {
    data class List(
        val onAdd: () -> Unit = {}
    ) : MemoListUiState
}