package com.android.diary.domain.deeplink

import com.android.diary.domain.constant.Parameter

object DeepLink {
    private val APP = DeepLinkBuilder(scheme = "diary")
    val MAIN = APP.setHost("main").build()

    private val MEMO_BUILDER = APP.setHost("memo")
    val MEMO = MEMO_BUILDER.build()
    val MEMO_LIST = MEMO_BUILDER.addPath("list").build()

    private val MEMO_DETAIL_BUILDER = MEMO_BUILDER.addPath("detail")
    val MEMO_DETAIL = MEMO_DETAIL_BUILDER.addPath("{${Parameter.ID}}").build()

    fun getMemoDetailLink(id: Long = 0L) = MEMO_DETAIL_BUILDER.addPath(id).build()
}