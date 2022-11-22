package com.diary.android.presenter.calendar.action

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn

interface CalendarSummaryAction {
    data class NavigateToMemoDetail(
        val beginDate: Int = Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays(),
        val endDate: Int = Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays()
    ) : CalendarSummaryAction
}
