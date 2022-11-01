package com.diary.android.presenter.memo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.diary.domain.usecase.PagingMemoUseCase
import com.android.diary.domain.utils.mapPaging
import com.android.diary.ui.uistate.memo.MemoListUiState
import com.android.diary.ui.uistate.memo.MemoUiState
import com.diary.android.presenter.memo.action.MemoListAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoListViewModel @Inject constructor(
    pagingMemoUseCase: PagingMemoUseCase
) : ViewModel() {
    private val _action = MutableSharedFlow<MemoListAction>()
    val action = _action.asSharedFlow()

    val uiState = MemoListUiState.List(
        onAdd = ::navigateUp
    )

    val memoUiState = pagingMemoUseCase(Unit).onFailure {
        viewModelScope.launch { _action.emit(MemoListAction.Failure(it)) }
    }.getOrDefault(emptyFlow()).mapPaging {
        MemoUiState.Simple(
            id = it.id,
            title = it.title
        )
    }

    private fun navigateUp() = viewModelScope.launch {
        _action.emit(MemoListAction.Add)
    }
}