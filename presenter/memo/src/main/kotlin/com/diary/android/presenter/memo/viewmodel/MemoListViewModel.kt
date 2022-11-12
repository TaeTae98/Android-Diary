package com.diary.android.presenter.memo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.android.domain.model.Id
import com.diary.android.domain.usecase.memo.DeleteMemoUseCase
import com.diary.android.domain.usecase.memo.PagingMemoUseCase
import com.diary.android.domain.utils.mapPaging
import com.diary.android.presenter.memo.action.MemoListAction
import com.diary.android.presenter.ui.uistate.memo.MemoListUiState
import com.diary.android.presenter.ui.uistate.memo.MemoUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MemoListViewModel @Inject constructor(
    pagingMemoUseCase: PagingMemoUseCase,
    private val deleteMemoUseCase: DeleteMemoUseCase
) : ViewModel() {
    private val _action = MutableSharedFlow<MemoListAction>()
    val action = _action.asSharedFlow()

    val uiState = MemoListUiState.List(
        onAdd = ::add
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

    private fun add(
        id: String = UUID.randomUUID().toString(),
        isNew: Boolean = true,
    ) = viewModelScope.launch {
        _action.emit(MemoListAction.NavigateToDetail(id = id, isNew = isNew))
    }

    private fun detail(
        id: String = UUID.randomUUID().toString(),
        isNew: Boolean = false,
    ) = viewModelScope.launch {
        _action.emit(MemoListAction.NavigateToDetail(id = id, isNew = isNew))
    }

    private fun delete(
        id: String = UUID.randomUUID().toString()
    ) = viewModelScope.launch {
        deleteMemoUseCase(Id(id))
    }
}
