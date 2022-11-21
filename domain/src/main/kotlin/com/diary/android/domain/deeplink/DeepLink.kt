package com.diary.android.domain.deeplink

import com.diary.android.domain.constant.Parameter
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import java.util.UUID

object DeepLink {
    private val APP = DeepLinkBuilder(scheme = "diary")
    val MAIN = APP.setHost("main").build()

    private val MEMO_BUILDER = APP.setHost("memo")
    val MEMO = MEMO_BUILDER.build()
    val MEMO_LIST = MEMO_BUILDER.addPath("list").build()

    private val MEMO_DETAIL_BUILDER = MEMO_BUILDER.addPath("detail")
    val MEMO_DETAIL = MEMO_DETAIL_BUILDER
        .addPath("{${Parameter.ID}}")
        .putQuery(Parameter.IS_NEW, "{${Parameter.IS_NEW}}")
        .putQuery(Parameter.BEGIN_DATE, "{${Parameter.BEGIN_DATE}}")
        .putQuery(Parameter.END_DATE, "{${Parameter.END_DATE}}")
        .build()

    private val CALENDAR_BUILDER = APP.setHost("calendar")
    val CALENDAR = CALENDAR_BUILDER.build()
    val CALENDAR_SUMMARY = CALENDAR_BUILDER
        .addPath("summary")
        .putQuery(Parameter.DATE, "{${Parameter.DATE}}")
        .build()

    private val MORE_BUILDER = APP.setHost("more")
    val MORE = MORE_BUILDER.build()
    val MORE_LIST = MORE_BUILDER.addPath("list").build()
    val ACCOUNT = MORE_BUILDER.addPath("account").build()

    fun getMemoDetailLink(
        id: String = UUID.randomUUID().toString(),
        isNew: Boolean = true,
        beginDate: Int = Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays(),
        endDate: Int = Clock.System.todayIn(TimeZone.currentSystemDefault()).toEpochDays(),
    ) = MEMO_DETAIL_BUILDER
        .addPath(id)
        .putQuery(Parameter.IS_NEW, isNew)
        .putQuery(Parameter.BEGIN_DATE, beginDate)
        .putQuery(Parameter.END_DATE, endDate)
        .build()
}
