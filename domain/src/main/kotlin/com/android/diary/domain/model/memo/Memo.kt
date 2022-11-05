package com.android.diary.domain.model.memo

import java.util.*

data class Memo(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
    val updatedAt: Long = System.currentTimeMillis()
)