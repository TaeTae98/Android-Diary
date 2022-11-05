package com.android.diary.domain.repository

import androidx.paging.PagingData
import com.android.diary.domain.model.memo.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun upsert(memo: Memo, userId: String?)
    suspend fun upsert(memo: Collection<Memo>, userId: String?)

    suspend fun sync(memo: Collection<Memo>, userId: String?)

    suspend fun findById(id: String): Memo?
    suspend fun findMigrationData(): List<Memo>
    suspend fun findSyncData(userId: String, updatedAt: Long): List<Memo>

    suspend fun deleteById(id: String)

    fun pagingByUserId(userId: String?): Flow<PagingData<Memo>>
}