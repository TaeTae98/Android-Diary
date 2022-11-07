package com.android.diary.domain.deeplink

import com.android.diary.domain.constant.Parameter
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
        .build()

    private val MORE_BUILDER = APP.setHost("more")
    val MORE = MORE_BUILDER.build()
    val MORE_LIST = MORE_BUILDER.addPath("list").build()
    val ACCOUNT = MORE_BUILDER.addPath("account").build()

    fun getMemoDetailLink(
        id: String = UUID.randomUUID().toString(),
        isNew: Boolean = true
    ) = MEMO_DETAIL_BUILDER
        .addPath(id)
        .putQuery(Parameter.IS_NEW, isNew)
        .build()
}
