package com.android.diary.share

import androidx.navigation.NavController
import com.android.diary.domain.deeplink.DeepLink
import java.util.*

fun NavController.navigateToMemoDetail(
    id: String = UUID.randomUUID().toString(),
    isNew: Boolean = true
) = navigate(DeepLink.getMemoDetailLink(id = id, isNew = isNew))

fun NavController.navigateToAccount() = navigate(DeepLink.ACCOUNT)
fun NavController.navigateToBackup() = navigate(DeepLink.BACKUP)