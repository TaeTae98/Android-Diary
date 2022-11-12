package com.diary.android.domain.deeplink

import com.diary.android.domain.constant.Parameter
import java.util.UUID

object DeepLink {
    private val APP = com.diary.android.domain.deeplink.DeepLinkBuilder(scheme = "diary")
    val MAIN = com.diary.android.domain.deeplink.DeepLink.APP.setHost("main").build()

    private val MEMO_BUILDER = com.diary.android.domain.deeplink.DeepLink.APP.setHost("memo")
    val MEMO = com.diary.android.domain.deeplink.DeepLink.MEMO_BUILDER.build()
    val MEMO_LIST = com.diary.android.domain.deeplink.DeepLink.MEMO_BUILDER.addPath("list").build()

    private val MEMO_DETAIL_BUILDER = com.diary.android.domain.deeplink.DeepLink.MEMO_BUILDER.addPath("detail")
    val MEMO_DETAIL = com.diary.android.domain.deeplink.DeepLink.MEMO_DETAIL_BUILDER
        .addPath("{${com.diary.android.domain.constant.Parameter.ID}}")
        .putQuery(com.diary.android.domain.constant.Parameter.IS_NEW, "{${com.diary.android.domain.constant.Parameter.IS_NEW}}")
        .build()

    private val MORE_BUILDER = com.diary.android.domain.deeplink.DeepLink.APP.setHost("more")
    val MORE = com.diary.android.domain.deeplink.DeepLink.MORE_BUILDER.build()
    val MORE_LIST = com.diary.android.domain.deeplink.DeepLink.MORE_BUILDER.addPath("list").build()
    val ACCOUNT = com.diary.android.domain.deeplink.DeepLink.MORE_BUILDER.addPath("account").build()

    fun getMemoDetailLink(
        id: String = UUID.randomUUID().toString(),
        isNew: Boolean = true
    ) = com.diary.android.domain.deeplink.DeepLink.MEMO_DETAIL_BUILDER
        .addPath(id)
        .putQuery(com.diary.android.domain.constant.Parameter.IS_NEW, isNew)
        .build()
}
