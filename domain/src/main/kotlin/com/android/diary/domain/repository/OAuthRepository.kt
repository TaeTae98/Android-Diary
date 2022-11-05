package com.android.diary.domain.repository

import com.android.diary.domain.model.AccountType
import com.android.diary.domain.model.DiaryAccount
import kotlinx.coroutines.flow.Flow

interface OAuthRepository {
    fun getAccount(): Flow<DiaryAccount>
    suspend fun signIn(idToken: String?, type: AccountType)
    suspend fun signOut()
}