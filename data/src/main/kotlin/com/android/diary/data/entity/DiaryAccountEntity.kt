package com.android.diary.data.entity

import com.android.diary.domain.model.DiaryAccount
import com.google.firebase.auth.FirebaseUser

sealed interface DiaryAccountEntity {
    fun toDomain(): DiaryAccount

    data class User(
        val id: String = "",
        val name: String? = null,
        val email: String? = null,
    ) : DiaryAccountEntity {
        constructor(firebaseUser: FirebaseUser) : this(
            id = firebaseUser.uid,
            name = firebaseUser.displayName,
            email = firebaseUser.email
        )

        override fun toDomain() = DiaryAccount.User(
            name = name,
            email = email
        )
    }

    object Guest : DiaryAccountEntity {
        override fun toDomain() = DiaryAccount.Guest
    }

    companion object {
        fun from(firebaseUser: FirebaseUser?) = firebaseUser?.let {
            User(it)
        } ?: Guest
    }
}