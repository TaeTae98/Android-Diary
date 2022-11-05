package com.diary.android.presenter.memo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.android.diary.domain.constant.Const
import com.android.diary.domain.constant.Parameter
import com.android.diary.domain.deeplink.DeepLink
import com.diary.android.presenter.memo.screen.MemoDetailScreen
import com.diary.android.presenter.memo.screen.MemoListScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import java.util.*

@OptIn(ExperimentalAnimationApi::class)
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
                nullable = false
                defaultValue = true
            }
        )
    ) {
        MemoDetailScreen(navController = navController)
    }
}