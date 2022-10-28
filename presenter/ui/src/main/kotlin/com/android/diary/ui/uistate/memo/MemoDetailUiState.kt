package com.android.diary.ui.uistate.memo

import androidx.compose.runtime.Immutable
import com.android.diary.ui.uistate.core.TextInputUiState

sealed class MemoDetailUiState {
    abstract val onNavigateUp: () -> Unit
    abstract val titleUiState: TextInputUiState
    abstract val descriptionUiState: TextInputUiState

    data class Add(
        override val onNavigateUp: () -> Unit = {},
        override val titleUiState: TextInputUiState = TextInputUiState(),
        override val descriptionUiState: TextInputUiState = TextInputUiState(),
        val onAdd: () -> Unit = {},
    ) : MemoDetailUiState()

    data class Detail(
        override val onNavigateUp: () -> Unit = {},
        override val titleUiState: TextInputUiState = TextInputUiState(),
        override val descriptionUiState: TextInputUiState = TextInputUiState()
    ) : MemoDetailUiState()
}