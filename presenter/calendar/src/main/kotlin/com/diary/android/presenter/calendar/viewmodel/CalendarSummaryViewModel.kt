package com.diary.android.presenter.calendar.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.android.presenter.calendar.action.CalendarSummaryAction
import com.diary.android.presenter.ui.uistate.calendar.CalendarSummaryScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarSummaryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _action = MutableSharedFlow<CalendarSummaryAction>()
    val action = _action.asSharedFlow()

    private val _uiState = MutableStateFlow(CalendarSummaryScreenUiState(::pickDateRange))
    val uiState = _uiState.asStateFlow()

    private fun pickDateRange(beginDate: LocalDate, endDate: LocalDate) = viewModelScope.launch {
        _action.emit(
            CalendarSummaryAction.NavigateToMemoDetail(
                beginDate = beginDate,
                endDate = endDate
            )
        )
    }
}
