package com.diary.android.domain.model.memo

import java.util.UUID

data class Memo(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
    val beginDate: Int? = null,
    val endDate: Int? = null,
    val updatedAt: Long = System.currentTimeMillis()
)
