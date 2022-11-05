package com.android.diary.ui.uistate.memo

import java.util.*

sealed interface MemoUiState {
    val id: String
    val title: String

    data class Simple(
        override val id: String = UUID.randomUUID().toString(),
        override val title: String = "",
        private val onClickMemo: (id: String) -> Unit = {},
        private val onDeleteMemo: (id: String) -> Unit = {},
    ) : MemoUiState {
        val onClick = { onClickMemo(id) }
        val onDelete = { onDeleteMemo(id) }
    }
}