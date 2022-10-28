package com.diary.android.presenter.memo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.diary.ui.uistate.memo.MemoListUiState
import com.diary.android.presenter.memo.action.MemoListAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _action = MutableSharedFlow<MemoListAction>()
    val action = _action.asSharedFlow()

    val uiState = MemoListUiState.List(
        onAdd = ::navigateUp
    )

    private fun navigateUp() = viewModelScope.launch {
        _action.emit(MemoListAction.Add)
    }
}