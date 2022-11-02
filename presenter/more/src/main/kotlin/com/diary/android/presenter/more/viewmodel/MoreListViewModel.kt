package com.diary.android.presenter.more.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.diary.share.oauth.GoogleOAuth
import com.android.diary.ui.uistate.more.MoreListUiState
import com.diary.android.presenter.more.action.MoreListAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreListViewModel @Inject constructor(

) : ViewModel() {
    private val _action = MutableSharedFlow<MoreListAction>()
    val action = _action.asSharedFlow()

    val uiState = MutableStateFlow(MoreListUiState(onAccount = ::account))

    private fun account() = viewModelScope.launch {
        _action.emit(MoreListAction.Account)
    }
}