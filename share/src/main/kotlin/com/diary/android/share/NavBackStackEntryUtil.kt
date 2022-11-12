package com.android.diary.share

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import com.diary.android.domain.deeplink.DeepLink

fun NavBackStackEntry.isSelected(
    route: String
) = destination.hierarchy.any { it.route == route }

fun NavBackStackEntry.isMainBottomBarVisible() = destination.route in arrayOf(
    com.diary.android.domain.deeplink.DeepLink.MEMO_LIST, "payment", "tag", "file", com.diary.android.domain.deeplink.DeepLink.MORE_LIST
)
