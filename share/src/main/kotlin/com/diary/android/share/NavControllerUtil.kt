package com.android.diary.share

import androidx.navigation.NavController
import com.diary.android.domain.deeplink.DeepLink
import java.util.UUID

fun NavController.navigateToMemoDetail(
    id: String = UUID.randomUUID().toString(),
    isNew: Boolean = true
) = navigate(com.diary.android.domain.deeplink.DeepLink.getMemoDetailLink(id = id, isNew = isNew))

fun NavController.navigateToAccount() = navigate(com.diary.android.domain.deeplink.DeepLink.ACCOUNT)
