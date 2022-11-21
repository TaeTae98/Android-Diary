package com.diary.android.share

import androidx.navigation.NavController
import com.diary.android.domain.deeplink.DeepLink
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.util.UUID

fun NavController.navigateToMemoDetail(
    id: String = UUID.randomUUID().toString(),
    isNew: Boolean = true,
    beginDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault()),
    endDate: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
) = navigate(DeepLink.getMemoDetailLink(id = id, isNew = isNew, beginDate = beginDate.toEpochDays(), endDate = endDate.toEpochDays()))

fun NavController.navigateToAccount() = navigate(DeepLink.ACCOUNT)
