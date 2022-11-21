package com.diary.android.presenter.calendar.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diary.android.presenter.calendar.action.CalendarSummaryAction
import com.diary.android.presenter.calendar.viewmodel.CalendarSummaryViewModel
import com.diary.android.presenter.ui.compose.calendar.CalendarSummaryScreenCompose
import com.diary.android.share.navigateToMemoDetail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CalendarSummaryScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    calendarSummaryViewModel: CalendarSummaryViewModel = hiltViewModel()
) = CalendarSummaryScreenCompose(
    modifier = modifier,
    uiState = calendarSummaryViewModel.uiState.collectAsState().value
).also {
    LaunchedEffect(calendarSummaryViewModel) {
        calendarSummaryViewModel.action.collectLatest {
            when (it) {
                is CalendarSummaryAction.NavigateToMemoDetail -> navController.navigateToMemoDetail(
                    beginDate = it.beginDate,
                    endDate = it.endDate
                )
            }
        }
    }
}
