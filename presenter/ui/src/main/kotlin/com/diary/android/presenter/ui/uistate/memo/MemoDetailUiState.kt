package com.diary.android.presenter.ui.uistate.memo

import com.diary.android.presenter.ui.uistate.core.ComponentDateRangeUiState
import com.diary.android.presenter.ui.uistate.core.TextInputUiState

sealed interface MemoDetailUiState {
    val onNavigateUp: () -> Unit
    val titleUiState: TextInputUiState
    val descriptionUiState: TextInputUiState
    val dateRangeUiState: ComponentDateRangeUiState

    data class Add(
        override val onNavigateUp: () -> Unit = {},
        override val titleUiState: TextInputUiState = TextInputUiState(),
        override val descriptionUiState: TextInputUiState = TextInputUiState(),
        override val dateRangeUiState: ComponentDateRangeUiState = ComponentDateRangeUiState(),
        val onAdd: () -> Unit = {},
    ) : MemoDetailUiState

    data class Detail(
        override val onNavigateUp: () -> Unit = {},
        override val titleUiState: TextInputUiState = TextInputUiState(),
        override val descriptionUiState: TextInputUiState = TextInputUiState(),
        override val dateRangeUiState: ComponentDateRangeUiState = ComponentDateRangeUiState(),
    ) : MemoDetailUiState
}
