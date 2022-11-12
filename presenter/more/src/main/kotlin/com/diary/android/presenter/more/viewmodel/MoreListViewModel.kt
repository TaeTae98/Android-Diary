package com.diary.android.presenter.more.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.android.presenter.more.action.MoreListAction
import com.diary.android.presenter.ui.uistate.more.MoreListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreListViewModel @Inject constructor() : ViewModel() {
    private val _action = MutableSharedFlow<MoreListAction>()
    val action = _action.asSharedFlow()

    private val _uiState = MutableStateFlow(
        MoreListUiState(
            onAccount = ::account,
        )
    )
    val uiState = _uiState.asStateFlow()

    private fun account() = viewModelScope.launch {
        _action.emit(MoreListAction.Account)
    }
}
