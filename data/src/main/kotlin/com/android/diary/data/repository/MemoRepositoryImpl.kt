package com.android.diary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.android.diary.data.datasource.MemoRemoteDataSource
import com.android.diary.data.datasource.MemoRoomDataSource
import com.android.diary.data.entity.MemoEntity
import com.android.diary.domain.model.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.utils.DEFAULT_PAGING_SIZE
import com.android.diary.domain.utils.mapPaging
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoRoomDataSource: MemoRoomDataSource,
    private val memoRemoteDataSource: MemoRemoteDataSource
) : MemoRepository {
    override suspend fun upsert(memo: Memo, userId: String?) {
        val entity = MemoEntity(memo = memo, userId = userId)
        memoRoomDataSource.upsert(entity)
        memoRemoteDataSource.upsert(entity)
    }

    override suspend fun findById(id: String) = memoRoomDataSource.findById(id)?.toDomain()
    override suspend fun deleteById(id: String) = memoRoomDataSource.deleteById(id)
    override suspend fun migration(userId: String?) = memoRoomDataSource.migration(userId)

    override fun pagingByUserId(userId: String?) = Pager(
        config = PagingConfig(pageSize = DEFAULT_PAGING_SIZE),
        initialKey = null,
    ) {
        memoRoomDataSource.pagingByUserId(userId)
    }.mapPaging(MemoEntity::toDomain)
}