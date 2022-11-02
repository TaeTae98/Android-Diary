package com.diary.android.presenter.memo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.diary.domain.model.Id
import com.android.diary.domain.usecase.memo.DeleteMemoByIdUseCase
import com.android.diary.domain.usecase.memo.PagingMemoUseCase
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
    pagingMemoUseCase: PagingMemoUseCase,
    private val deleteMemoByIdUseCase: DeleteMemoByIdUseCase
) : ViewModel() {
    private val _action = MutableSharedFlow<MemoListAction>()
    val action = _action.asSharedFlow()

    val uiState = MemoListUiState.List(
        onAdd = ::detail
    )

    val memoUiState = pagingMemoUseCase(Unit).onFailure {
        viewModelScope.launch { _action.emit(MemoListAction.Failure(it)) }
    }.getOrDefault(emptyFlow()).mapPaging {
        MemoUiState.Simple(
            id = it.id,
            title = it.title,
            onClickMemo = ::detail,
            onDeleteMemo = ::delete
        )
    }

    private fun detail(id: Long = 0L) = viewModelScope.launch {
        _action.emit(MemoListAction.NavigateToDetail(id))
    }

    private fun delete(id: Long = 0L) = viewModelScope.launch {
        deleteMemoByIdUseCase(Id(id))
    }
}