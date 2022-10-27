package com.diary.android.presenter.memo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.diary.domain.DeepLink
import com.diary.android.presenter.memo.screen.MemoListScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

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
}