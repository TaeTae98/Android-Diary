package com.diary.android.presenter.calendar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.diary.android.domain.constant.Parameter
import com.diary.android.domain.deeplink.DeepLink
import com.diary.android.presenter.calendar.screen.CalendarSummaryScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

fun NavGraphBuilder.calendarGraph(
    navController: NavController
) = navigation(
    startDestination = DeepLink.CALENDAR_SUMMARY,
    route = DeepLink.CALENDAR
) {
    composable(
        route = DeepLink.CALENDAR_SUMMARY,
        arguments = listOf(
            navArgument(name = Parameter.DATE) {
                type = NavType.IntType
            }
        )
    ) {
        CalendarSummaryScreen(navController = navController)
    }
}
