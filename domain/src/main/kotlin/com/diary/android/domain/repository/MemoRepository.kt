package com.diary.android.domain.repository

import androidx.paging.PagingData
import com.diary.android.domain.model.memo.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun upsert(memo: Memo)
    suspend fun findById(id: String): Memo?
    suspend fun deleteById(id: String)

    suspend fun migration()
    suspend fun sync()

    fun pagingAll(): Flow<PagingData<Memo>>
}
