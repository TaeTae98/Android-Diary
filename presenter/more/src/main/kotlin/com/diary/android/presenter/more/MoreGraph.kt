package com.diary.android.presenter.more

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.diary.android.domain.deeplink.DeepLink
import com.diary.android.presenter.more.screen.AccountScreen
import com.diary.android.presenter.more.screen.MoreListScreen
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation

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
