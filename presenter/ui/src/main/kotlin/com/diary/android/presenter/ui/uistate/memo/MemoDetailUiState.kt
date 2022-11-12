package com.diary.android.presenter.ui.uistate.memo

import com.diary.android.presenter.ui.uistate.core.TextInputUiState

sealed interface MemoDetailUiState {
    val onNavigateUp: () -> Unit
    val titleUiState: TextInputUiState
    val descriptionUiState: TextInputUiState

    data class Add(
        override val onNavigateUp: () -> Unit = {},
        override val titleUiState: TextInputUiState = TextInputUiState(),
        override val descriptionUiState: TextInputUiState = TextInputUiState(),
        val onAdd: () -> Unit = {},
    ) : MemoDetailUiState

    data class Detail(
        override val onNavigateUp: () -> Unit = {},
        override val titleUiState: TextInputUiState = TextInputUiState(),
        override val descriptionUiState: TextInputUiState = TextInputUiState(),
    ) : MemoDetailUiState
}
