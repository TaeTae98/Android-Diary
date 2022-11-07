package com.android.diary.domain.model

sealed interface DiaryAccount {
    val id: String?

    object Guest : DiaryAccount {
        override val id: String? = null
    }

    data class User(
        override val id: String,
        val name: String?,
        val email: String?,
    ) : DiaryAccount
}
