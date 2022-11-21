package com.diary.android.presenter.calendar.action

import kotlinx.datetime.LocalDate

interface CalendarSummaryAction {
    data class NavigateToMemoDetail(val beginDate: LocalDate, val endDate: LocalDate) : CalendarSummaryAction
}
