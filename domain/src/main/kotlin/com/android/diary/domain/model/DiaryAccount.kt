package com.android.diary.domain.model

sealed class DiaryAccount {
    object Guest : DiaryAccount()

    data class User(
        val name: String?,
        val email: String?,
    ) : DiaryAccount() {
        val displayName = name ?: email.orEmpty()
    }
}