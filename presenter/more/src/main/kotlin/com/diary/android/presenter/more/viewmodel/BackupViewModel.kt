package com.diary.android.presenter.more.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.diary.domain.usecase.backup.DownloadDataUseCase
import com.android.diary.domain.usecase.backup.UploadDataUseCase
import com.android.diary.ui.uistate.backup.BackupUiState
import com.diary.android.presenter.more.action.BackupAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BackupViewModel @Inject constructor(
    private val uploadDataUseCase: UploadDataUseCase,
    private val downloadDataUseCase: DownloadDataUseCase,
) : ViewModel() {
    private val _action = MutableSharedFlow<BackupAction>()
    val action = _action.asSharedFlow()

    private val _uiState = MutableStateFlow(
        BackupUiState(
            onNavigateUp = ::navigateUp,
            onUpload = ::upload,
            onDownload = ::download
        )
    )
    val uiState = _uiState.asStateFlow()

    private fun navigateUp() = viewModelScope.launch {
        _action.emit(BackupAction.NavigateUp)
    }

    private fun upload() = uploadDataUseCase(Unit)

    private fun download() = downloadDataUseCase(Unit)
}