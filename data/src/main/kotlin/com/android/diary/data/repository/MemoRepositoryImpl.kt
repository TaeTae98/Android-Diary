package com.android.diary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.android.diary.data.datasource.MemoRemoteDataSource
import com.android.diary.data.datasource.MemoRoomDataSource
import com.android.diary.data.entity.MemoEntity
import com.android.diary.domain.model.memo.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.utils.DEFAULT_PAGING_SIZE
import com.android.diary.domain.utils.isNotNull
import com.android.diary.domain.utils.mapPaging
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoRepositoryImpl @Inject constructor(
    private val memoRoomDataSource: MemoRoomDataSource,
    private val memoRemoteDataSource: MemoRemoteDataSource
) : MemoRepository {
    override suspend fun upsert(memo: Memo, userId: String?) {
        val entity = MemoEntity(memo = memo, userId = userId)

        memoRoomDataSource.upsert(entity)
        if (userId.isNotNull()) {
            memoRemoteDataSource.upsert(entity)
        }
    }

    override suspend fun upsert(memo: Collection<Memo>, userId: String?) {
        val entities = memo.map { MemoEntity(it, userId) }

        memoRoomDataSource.upsert(entities)
        if (userId.isNotNull()) {
            memoRemoteDataSource.upsert(entities)
        }
    }

    override suspend fun sync(memo: Collection<Memo>, userId: String?) {
        val entities = memo.map { MemoEntity(it, userId) }
        memoRoomDataSource.upsert(entities)
    }

    override suspend fun findById(id: String) = memoRoomDataSource.findById(id)?.toDomain()
    override suspend fun findMigrationData() =
        memoRoomDataSource.findMigrationData().map(MemoEntity::toDomain)

    override suspend fun findSyncData(
        userId: String,
        updatedAt: Long
    ) = memoRemoteDataSource.findSyncData(
        userId = userId,
        updatedAt = updatedAt
    ).map(MemoEntity::toDomain)

    override suspend fun deleteById(id: String) {
        memoRoomDataSource.deleteById(id)
        memoRemoteDataSource.deleteById(id)
    }


    override fun pagingByUserId(userId: String?) = Pager(
        config = PagingConfig(pageSize = DEFAULT_PAGING_SIZE),
        initialKey = null,
    ) {
        memoRoomDataSource.pagingByUserId(userId)
    }.mapPaging(MemoEntity::toDomain)
}