package com.android.diary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.android.diary.data.datasource.MemoRemoteDataSource
import com.android.diary.data.datasource.MemoRoomDataSource
import com.android.diary.data.datasource.OAuthDataSource
import com.android.diary.data.datasource.UserSettingDataStoreDataSource
import com.android.diary.data.entity.MemoEntity
import com.android.diary.domain.model.memo.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.utils.DEFAULT_PAGING_SIZE
import com.android.diary.domain.utils.isNotNull
import com.android.diary.domain.utils.mapPaging
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoRepositoryImpl @Inject constructor(
    private val oAuthDataSource: OAuthDataSource,
    private val memoRoomDataSource: MemoRoomDataSource,
    private val memoRemoteDataSource: MemoRemoteDataSource,
    private val userSettingDataStoreDataSource: UserSettingDataStoreDataSource
) : MemoRepository {
    override suspend fun upsert(memo: Memo) {
        val userId = oAuthDataSource.user.value.id
        val entity = MemoEntity(memo = memo, userId = userId)

        memoRoomDataSource.upsert(entity)
        if (userId.isNotNull()) {
            memoRemoteDataSource.upsert(entity)
        }
    }

    override suspend fun migration() {
        val userId = oAuthDataSource.user.value.id ?: return
        val entities = memoRoomDataSource.findMigrationData().map { it.copy(userId = userId) }
        memoRoomDataSource.upsert(entities)
        memoRemoteDataSource.upsert(entities)
    }

    override suspend fun sync() {
        val userId = oAuthDataSource.user.value.id ?: return
        while (true) {
            val updatedAt = userSettingDataStoreDataSource.getMemoLastUpdatedAt(userId)
                .firstOrNull()
                ?.takeIf { it != 0L }?.plus(1L) ?: 0L
            val data = memoRemoteDataSource.findSyncData(userId = userId, updatedAt = updatedAt)
            val lastUpdatedAt = data.lastOrNull()?.updatedAt ?: break

            memoRoomDataSource.upsert(data)
            userSettingDataStoreDataSource.setMemoLastUpdatedAt(
                userId = userId,
                updatedAt = lastUpdatedAt
            )
        }
    }

    override suspend fun findById(id: String) = memoRoomDataSource.findById(id)?.toDomain()

    override suspend fun deleteById(id: String) {
        memoRoomDataSource.deleteById(id)
        memoRemoteDataSource.deleteById(id)
    }


    override fun pagingAll() = Pager(
        config = PagingConfig(pageSize = DEFAULT_PAGING_SIZE),
        initialKey = null,
    ) {
        memoRoomDataSource.pagingByUserId(oAuthDataSource.user.value.id)
    }.mapPaging(MemoEntity::toDomain)
}