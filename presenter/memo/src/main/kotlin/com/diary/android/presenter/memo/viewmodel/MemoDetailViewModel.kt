package com.diary.android.presenter.memo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.diary.domain.Parameter
import com.android.diary.domain.model.Memo
import com.android.diary.domain.usecase.MemoUpsertUseCase
import com.android.diary.domain.utils.onFalse
import com.android.diary.domain.utils.onTrue
import com.android.diary.ui.uistate.core.TextInputUiState
import com.android.diary.ui.uistate.memo.MemoDetailUiState
import com.diary.android.presenter.memo.action.MemoDetailAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val memoUpsertUseCase: MemoUpsertUseCase,
) : ViewModel() {
    private val _action = MutableSharedFlow<MemoDetailAction>()
    val action = _action.asSharedFlow()

    private val id = savedStateHandle.getStateFlow(
        key = Parameter.ID,
        initialValue = 0L
    )

    private val isAddMode = id.map {
        it == 0L
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = id.value == 0L
    )

    private val title = savedStateHandle.getStateFlow(
        key = Parameter.TITLE,
        initialValue = ""
    )

    private val titleErrorAt = MutableStateFlow<Long?>(null)

    private val titleUiState = combine(title, titleErrorAt) { title, titleErrorAt ->
        TextInputUiState(
            value = title,
            onValueChange = ::setTitle,
            errorAt = titleErrorAt
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = TextInputUiState(
            value = title.value,
            onValueChange = ::setTitle,
            errorAt = titleErrorAt.value
        )
    )

    private val descriptionUiState = savedStateHandle.getStateFlow(
        key = Parameter.DESCRIPTION,
        initialValue = ""
    ).map { description ->
        TextInputUiState(
            value = description,
            onValueChange = ::setDescription
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = TextInputUiState(
            value = savedStateHandle[Parameter.DESCRIPTION] ?: "",
            onValueChange = ::setDescription
        )
    )

    val uiState = combine(
        isAddMode,
        titleUiState,
        descriptionUiState
    ) { isAddMode, titleUiState, descriptionUiState ->
        if (isAddMode) {
            MemoDetailUiState.Add(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState,
                descriptionUiState = descriptionUiState,
                onAdd = ::upsert
            )
        } else {
            MemoDetailUiState.Detail(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState,
                descriptionUiState = descriptionUiState
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = if (isAddMode.value) {
            MemoDetailUiState.Add(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState.value,
                descriptionUiState = descriptionUiState.value,
                onAdd = ::upsert
            )
        } else {
            MemoDetailUiState.Detail(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState.value,
                descriptionUiState = descriptionUiState.value
            )
        }
    )

    private fun navigateUp() = viewModelScope.launch {
        _action.emit(MemoDetailAction.NavigateUp)
    }

    private fun setTitle(title: String) = savedStateHandle.set(
        key = Parameter.TITLE,
        value = title
    ).also {
        viewModelScope.launch {
            titleErrorAt.emit(null)
        }
    }

    private fun setDescription(description: String) = savedStateHandle.set(
        key = Parameter.DESCRIPTION,
        value = description
    )

    private fun upsert() = viewModelScope.launch {
        requireTitle().onFalse { return@launch }

        val memo = Memo(
            id = id.value,
            title = title.value,
            description = descriptionUiState.value.value
        )

        memoUpsertUseCase(memo).onSuccess {
            setTitle("")
            setDescription("")
            isAddMode.value.onTrue {
                _action.emit(MemoDetailAction.Add(memo.title))
            }
        }.onFailure {
            _action.emit(MemoDetailAction.Failure(it))
        }
    }

    private fun requireTitle() = title.value.isNotEmpty().onFalse {
        viewModelScope.launch {
            titleErrorAt.emit(System.currentTimeMillis())
            _action.emit(MemoDetailAction.TitleEmpty)
        }
    }
}