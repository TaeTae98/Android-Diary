package com.android.diary.ui.uistate.memo

sealed class MemoListUiState {
    data class List(
        val onAdd: () -> Unit = {}
    ) : MemoListUiState()
}