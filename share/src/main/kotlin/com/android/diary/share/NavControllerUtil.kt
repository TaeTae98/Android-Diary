package com.android.diary.share

import androidx.navigation.NavController
import com.android.diary.domain.deeplink.DeepLink

fun NavController.navigateToMemoDetail(
    id: Long = 0L
) = navigate(DeepLink.getMemoDetailLink(id = id))

fun NavController.navigateToAccount() = navigate(DeepLink.ACCOUNT)