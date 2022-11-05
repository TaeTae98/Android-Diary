package com.android.diary.domain.model

import java.util.*

data class Memo(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = ""
)