package com.diary.android.domain.repository

import com.diary.android.domain.model.DiaryAccount
import kotlinx.coroutines.flow.Flow

interface OAuthRepository {
    fun getAccount(): Flow<DiaryAccount>
    suspend fun signIn(idToken: String?)
    suspend fun signOut()
}
