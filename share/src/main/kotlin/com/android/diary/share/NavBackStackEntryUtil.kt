package com.android.diary.share

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import com.android.diary.domain.deeplink.DeepLink

fun NavBackStackEntry.isSelected(
    route: String
) = destination.hierarchy.any { it.route == route }

fun NavBackStackEntry.isMainBottomBarVisible() = destination.route in arrayOf(
    DeepLink.MEMO_LIST, "payment", "tag", "file", "more"
)