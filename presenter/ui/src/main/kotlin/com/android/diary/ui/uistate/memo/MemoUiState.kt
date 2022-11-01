package com.android.diary.ui.uistate.memo

sealed class MemoUiState {
    abstract val id: Long
    abstract val title: String

    data class Simple(
        override val id: Long = 0L,
        override val title: String = ""
    ) : MemoUiState()
}