package com.diary.android.presenter.memo

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.diary.android.domain.constant.Const
import com.diary.android.domain.constant.Parameter
import com.diary.android.domain.deeplink.DeepLink
import com.diary.android.presenter.memo.screen.MemoDetailScreen
import com.diary.android.presenter.memo.screen.MemoListScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

fun NavGraphBuilder.memoGraph(
    navController: NavController
) = navigation(
    startDestination = DeepLink.MEMO_LIST,
    route = DeepLink.MEMO
) {
    composable(route = DeepLink.MEMO_LIST) {
        MemoListScreen(navController = navController)
    }

    composable(
        route = DeepLink.MEMO_DETAIL,
        arguments = listOf(
            navArgument(name = Parameter.ID) {
                type = NavType.StringType
                nullable = false
                defaultValue = Const.INVALID_UUID
            },
            navArgument(name = Parameter.IS_NEW) {
                type = NavType.BoolType
                defaultValue = true
            }
        )
    ) {
        MemoDetailScreen(navController = navController)
    }
}
