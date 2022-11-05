package com.android.diary.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserSettingRepository {
    fun getMemoLastUpdatedAt(userId: String?): Flow<Long>
    suspend fun setMemoLastUpdatedAt(userId: String?, updatedAt: Long)
}