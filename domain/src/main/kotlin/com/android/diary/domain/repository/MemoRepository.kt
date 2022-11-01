package com.android.diary.domain.repository

import androidx.paging.PagingData
import com.android.diary.domain.model.Memo
import kotlinx.coroutines.flow.Flow

interface MemoRepository {
    suspend fun upsert(memo: Memo)
    suspend fun findById(id: Long): Memo?

    fun pagingAll(): Flow<PagingData<Memo>>
}