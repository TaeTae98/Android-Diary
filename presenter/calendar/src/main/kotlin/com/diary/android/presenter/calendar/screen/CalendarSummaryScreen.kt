package com.diary.android.presenter.calendar.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.diary.android.presenter.ui.compose.calendar.CalendarSummaryScreenCompose

@Composable
fun CalendarSummaryScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) = CalendarSummaryScreenCompose(
    modifier = modifier
)
