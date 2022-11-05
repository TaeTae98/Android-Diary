package com.android.diary.domain.repository

import androidx.paging.PagingData
import com.android.diary.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun upsert(memo: Memo, userId: String?)
    suspend fun findById(id: String): Memo?
    suspend fun deleteById(id: String): Int
    suspend fun migration(userId: String?): Int

    fun pagingByUserId(userId: String?): Flow<PagingData<Memo>>
}