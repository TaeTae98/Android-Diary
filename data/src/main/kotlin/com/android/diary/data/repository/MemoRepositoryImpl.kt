package com.android.diary.data.repository

import com.android.diary.data.datasource.MemoRoomDataSource
import com.android.diary.data.entity.MemoEntity
import com.android.diary.domain.model.Memo
import com.android.diary.domain.repository.MemoRepository
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoRoomDataSource: MemoRoomDataSource
) : MemoRepository {
    override suspend fun upsert(memo: Memo) = memoRoomDataSource.upsert(MemoEntity(memo))
}