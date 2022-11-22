package com.diary.android.presenter.ui.uistate.calendar

data class CalendarSummaryScreenUiState(
    val onPickDateRange: (beginDate: Int, endDate: Int) -> Unit = { _, _ -> }
)
