package com.diary.android.share

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import com.diary.android.domain.deeplink.DeepLink

fun NavBackStackEntry.isSelected(
    route: String
) = destination.hierarchy.any { it.route == route }

fun NavBackStackEntry.isMainBottomBarVisible() = destination.route in arrayOf(
    DeepLink.MEMO_LIST, DeepLink.CALENDAR_SUMMARY, "payment", "tag", DeepLink.MORE_LIST
)
