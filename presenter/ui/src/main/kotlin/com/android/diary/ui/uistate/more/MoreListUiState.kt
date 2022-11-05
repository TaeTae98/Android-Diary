package com.android.diary.ui.uistate.more

data class MoreListUiState(
    val onAccount: () -> Unit = {},
    val onBackup: () -> Unit = {}
)