package com.android.diary.ui.uistate.memo

sealed class MemoUiState {
    abstract val id: Long
    abstract val title: String

    data class Simple(
        override val id: Long = 0L,
        override val title: String = "",
        private val onClickMemo: (id: Long) -> Unit = {},
        private val onDeleteMemo: (id: Long) -> Unit = {},
    ) : MemoUiState() {
        val onClick = { onClickMemo(id) }
        val onDelete = { onDeleteMemo(id) }
    }
}