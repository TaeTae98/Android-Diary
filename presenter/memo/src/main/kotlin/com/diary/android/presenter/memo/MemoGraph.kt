package com.diary.android.presenter.memo

import androidx.compose.animation.ExperimentalAnimationApi
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

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.memoGraph(
    navController: NavController
) = navigation(
    startDestination = com.diary.android.domain.deeplink.DeepLink.MEMO_LIST,
    route = com.diary.android.domain.deeplink.DeepLink.MEMO
) {
    composable(route = com.diary.android.domain.deeplink.DeepLink.MEMO_LIST) {
        MemoListScreen(navController = navController)
    }

    composable(
        route = com.diary.android.domain.deeplink.DeepLink.MEMO_DETAIL,
        arguments = listOf(
            navArgument(name = com.diary.android.domain.constant.Parameter.ID) {
                type = NavType.StringType
                nullable = false
                defaultValue = com.diary.android.domain.constant.Const.INVALID_UUID
            },
            navArgument(name = com.diary.android.domain.constant.Parameter.IS_NEW) {
                type = NavType.BoolType
                nullable = false
                defaultValue = true
            }
        )
    ) {
        MemoDetailScreen(navController = navController)
    }
}
