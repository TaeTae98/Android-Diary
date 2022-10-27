package com.android.diary.domain

object DeepLink {
    private val APP = DeepLinkBuilder(scheme = "diary")
    val MAIN = APP.host("main").build()

    private val MEMO_BUILDER = APP.host("memo")
    val MEMO = MEMO_BUILDER.build()
    val MEMO_LIST = MEMO_BUILDER.path("list").build()
}