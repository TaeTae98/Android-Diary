package com.diary.android.presenter.ui.uistate.memo

sealed interface MemoListUiState {
    data class List(
        val onAdd: () -> Unit = {}
    ) : MemoListUiState
}
