package com.android.diary.ui.uistate.backup

data class BackupUiState(
    val onNavigateUp: () -> Unit = {},
    val onUpload: () -> Unit = {},
    val onDownload: () -> Unit = {}
)