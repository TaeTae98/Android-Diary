package com.diary.android.presenter.more

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.android.diary.domain.deeplink.DeepLink
import com.diary.android.presenter.more.screen.AccountScreen
import com.diary.android.presenter.more.screen.MoreListScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.moreGraph(
    navController: NavController
) = navigation(
    startDestination = DeepLink.MORE_LIST,
    route = DeepLink.MORE
) {
    composable(route = DeepLink.MORE_LIST) {
        MoreListScreen(navController = navController)
    }

    composable(route = DeepLink.ACCOUNT) {
        AccountScreen(navController = navController)
    }
}