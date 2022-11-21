package com.diary.android.presenter.memo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.android.domain.constant.Parameter
import com.diary.android.domain.model.Id
import com.diary.android.domain.model.memo.Memo
import com.diary.android.domain.usecase.memo.FindMemoUseCase
import com.diary.android.domain.usecase.memo.MemoUpsertUseCase
import com.diary.android.domain.utils.onFalse
import com.diary.android.domain.utils.onNullOrFalse
import com.diary.android.domain.utils.onTrue
import com.diary.android.presenter.memo.action.MemoDetailAction
import com.diary.android.presenter.ui.uistate.core.ComponentDateRangeUiState
import com.diary.android.presenter.ui.uistate.core.TextInputUiState
import com.diary.android.presenter.ui.uistate.memo.MemoDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class MemoDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val findMemoUseCase: FindMemoUseCase,
    private val memoUpsertUseCase: MemoUpsertUseCase,
) : ViewModel() {
    private val _action = MutableSharedFlow<MemoDetailAction>()
    val action = _action.asSharedFlow()

    private val id = savedStateHandle.getStateFlow(
        key = Parameter.ID,
        initialValue = ""
    ).map { id ->
        id.takeIf { it != "" } ?: UUID.randomUUID().toString()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ""
    )

    private val isNew = savedStateHandle.getStateFlow(
        key = Parameter.IS_NEW,
        initialValue = true
    )

    private val title = savedStateHandle.getStateFlow(
        key = Parameter.TITLE,
        initialValue = ""
    )

    private val beginDate = savedStateHandle.getStateFlow(
        key = Parameter.BEGIN_DATE,
        initialValue = 0
    )

    private val endDate = savedStateHandle.getStateFlow(
        key = Parameter.END_DATE,
        initialValue = 0
    )

    private val hasDate = savedStateHandle.getStateFlow(
        key = Parameter.HAS_DATE,
        initialValue = endDate.value != 0 && beginDate.value != 0
    )

    private val dateRangeUiState = combine(beginDate, endDate) { beginDate, endDate ->
        ComponentDateRangeUiState(
            beginDate = beginDate,
            endDate = endDate,
            setBeginDate = ::setBeginDate,
            setEndDate = ::setEndDate
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ComponentDateRangeUiState(
            beginDate = beginDate.value,
            endDate = endDate.value
        )
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
        isNew,
        titleUiState,
        descriptionUiState,
        dateRangeUiState,
    ) { isNew, titleUiState, descriptionUiState, dateRangeUiState ->
        if (isNew) {
            MemoDetailUiState.Add(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState,
                descriptionUiState = descriptionUiState,
                dateRangeUiState = dateRangeUiState,
                onAdd = ::upsert
            )
        } else {
            MemoDetailUiState.Detail(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState,
                descriptionUiState = descriptionUiState,
                dateRangeUiState = dateRangeUiState,
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = if (isNew.value) {
            MemoDetailUiState.Add(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState.value,
                descriptionUiState = descriptionUiState.value,
                dateRangeUiState = dateRangeUiState.value,
                onAdd = ::upsert
            )
        } else {
            MemoDetailUiState.Detail(
                onNavigateUp = ::navigateUp,
                titleUiState = titleUiState.value,
                dateRangeUiState = dateRangeUiState.value,
                descriptionUiState = descriptionUiState.value,
            )
        }
    )

    init {
        savedStateHandle.get<Boolean>(Parameter.IS_INITIALIZED).onNullOrFalse {
            viewModelScope.launch {
                findMemoUseCase(Id(id.value)).getOrNull()?.let { memo ->
                    setTitle(memo.title)
                    setDescription(memo.description)
                    savedStateHandle[Parameter.IS_INITIALIZED] = true
                }
            }
        }
    }

    private fun navigateUp() = viewModelScope.launch {
        isNew.value.onFalse { upsert().join() }
        _action.emit(MemoDetailAction.NavigateUp)
    }

    private fun setId(id: String = UUID.randomUUID().toString()) = savedStateHandle.set(
        key = Parameter.ID,
        value = id
    )

    private fun setTitle(title: String = "") = savedStateHandle.set(
        key = Parameter.TITLE,
        value = title
    ).also {
        viewModelScope.launch {
            titleErrorAt.emit(null)
        }
    }

    private fun setDescription(description: String = "") = savedStateHandle.set(
        key = Parameter.DESCRIPTION,
        value = description
    )

    private fun setBeginDate(beginDate: Int): Unit = savedStateHandle.set(
        key = Parameter.BEGIN_DATE,
        value = beginDate
    ).also {
        if (beginDate > endDate.value) {
            setEndDate(beginDate)
        }
    }

    private fun setEndDate(endDate: Int): Unit = savedStateHandle.set(
        key = Parameter.BEGIN_DATE,
        value = endDate
    ).also {
        if (beginDate.value > endDate) {
            setBeginDate(endDate)
        }
    }

    private fun upsert() = viewModelScope.launch {
        requireTitle().onFalse { return@launch }

        val memo = Memo(
            id = id.value,
            title = title.value,
            description = descriptionUiState.value.value
        )

        memoUpsertUseCase(memo).onSuccess {
            isNew.value.onTrue {
                setId()
                setTitle()
                setDescription()
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
