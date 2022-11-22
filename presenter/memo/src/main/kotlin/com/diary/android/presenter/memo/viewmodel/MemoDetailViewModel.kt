package com.diary.android.presenter.memo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.android.domain.constant.Const
import com.diary.android.domain.constant.Parameter
import com.diary.android.domain.model.Id
import com.diary.android.domain.model.memo.Memo
import com.diary.android.domain.usecase.memo.FindMemoUseCase
import com.diary.android.domain.usecase.memo.MemoUpsertUseCase
import com.diary.android.domain.utils.isNotNull
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
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
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
        initialValue = UUID.randomUUID().toString()
    ).map { id ->
        id.takeIf { it != Const.INVALID_ID } ?: UUID.randomUUID().toString()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UUID.randomUUID().toString()
    )

    private val isNew = savedStateHandle.getStateFlow(
        key = Parameter.IS_NEW,
        initialValue = true
    )

    private val title = savedStateHandle.getStateFlow(
        key = Parameter.TITLE,
        initialValue = ""
    )

    private val hasDate = savedStateHandle.getStateFlow(
        key = Parameter.HAS_DATE,
        initialValue = hasDefaultDate(Parameter.BEGIN_DATE) && hasDefaultDate(Parameter.END_DATE)
    )

    private val beginDate = savedStateHandle.getStateFlow(
        key = Parameter.BEGIN_DATE_EDIT,
        initialValue = getDefaultDate(Parameter.BEGIN_DATE)
    )

    private val endDate = savedStateHandle.getStateFlow(
        key = Parameter.END_DATE_EDIT,
        initialValue = getDefaultDate(Parameter.END_DATE)
    )

    private val dateRangeUiState = combine(hasDate, beginDate, endDate) { hasDate, beginDate, endDate ->
        ComponentDateRangeUiState(
            hasDate = hasDate,
            setHasDate = ::setHasDate,
            beginDate = beginDate,
            endDate = endDate,
            setBeginDate = ::setBeginDate,
            setEndDate = ::setEndDate
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ComponentDateRangeUiState(
            hasDate = hasDate.value,
            setHasDate = ::setHasDate,
            beginDate = beginDate.value,
            endDate = endDate.value,
            setBeginDate = ::setBeginDate,
            setEndDate = ::setEndDate
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
                descriptionUiState = descriptionUiState.value,
                dateRangeUiState = dateRangeUiState.value,
            )
        }
    )

    init {
        savedStateHandle.get<Boolean>(Parameter.IS_INITIALIZED).onNullOrFalse {
            viewModelScope.launch {
                findMemoUseCase(Id(id.value)).getOrNull()?.let { memo ->
                    setTitle(memo.title)
                    setDescription(memo.description)
                    setHasDate(memo.beginDate.isNotNull() && memo.endDate.isNotNull())
                    setBeginDate(memo.beginDate ?: Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays())
                    setEndDate(memo.endDate ?: Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays())
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

    private fun setHasDate(hasDate: Boolean = hasDefaultDate(Parameter.BEGIN_DATE) && hasDefaultDate(Parameter.END_DATE)) = savedStateHandle.set(
        key = Parameter.HAS_DATE,
        value = hasDate
    )

    private fun setBeginDate(beginDate: Int = getDefaultDate(Parameter.BEGIN_DATE)): Unit = savedStateHandle.set(
        key = Parameter.BEGIN_DATE_EDIT,
        value = beginDate
    ).also {
        if (beginDate > endDate.value) {
            setEndDate(beginDate)
        }
    }

    private fun setEndDate(endDate: Int = getDefaultDate(Parameter.BEGIN_DATE)): Unit = savedStateHandle.set(
        key = Parameter.END_DATE_EDIT,
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

    private fun hasDefaultDate(parameter: String) = savedStateHandle.get<Int>(parameter)?.takeIf { it != Const.INVALID_DATE }.isNotNull()

    private fun getDefaultDate(parameter: String) = savedStateHandle.get<Int>(parameter)?.takeIf {
        it != Const.INVALID_DATE
    } ?: Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays()
}
