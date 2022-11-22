package com.diary.android.share

import androidx.navigation.NavController
import com.diary.android.domain.deeplink.DeepLink
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.util.UUID

fun NavController.navigateToMemoDetail(
    id: String = UUID.randomUUID().toString(),
    isNew: Boolean = true,
    beginDate: Int = Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays(),
    endDate: Int = Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays()
) = navigate(DeepLink.getMemoDetailLink(id = id, isNew = isNew, beginDate = beginDate, endDate = endDate))

fun NavController.navigateToAccount() = navigate(DeepLink.ACCOUNT)
