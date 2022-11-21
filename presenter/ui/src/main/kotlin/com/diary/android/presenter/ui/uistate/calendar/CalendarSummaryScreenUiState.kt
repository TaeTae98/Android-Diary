package com.diary.android.presenter.ui.uistate.calendar

import kotlinx.datetime.LocalDate

data class CalendarSummaryScreenUiState(
    val onPickDateRange: (beginDate: LocalDate, endDate: LocalDate) -> Unit = { _, _ -> }
)
