package com.diary.android.presenter.ui.compose.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.presenter.ui.uistate.calendar.CalendarSummaryScreenUiState

@Composable
fun CalendarSummaryScreenCompose(
    modifier: Modifier = Modifier,
    uiState: CalendarSummaryScreenUiState = CalendarSummaryScreenUiState()
) = Calendar(
    modifier = modifier,
    onPickDateRange = uiState.onPickDateRange
)

@Preview
@Composable
private fun Preview() = CalendarSummaryScreenCompose()
