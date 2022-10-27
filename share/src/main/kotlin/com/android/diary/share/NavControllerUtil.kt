package com.android.diary.share

import androidx.navigation.NavController
import com.android.diary.domain.DeepLink

fun NavController.navigateToMemoDetail(id: Long = 0L) = navigate(
    DeepLink.getMemoDetailLink(id = id)
)