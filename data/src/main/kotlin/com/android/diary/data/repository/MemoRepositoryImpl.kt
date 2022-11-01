package com.android.diary.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.android.diary.data.datasource.MemoRoomDataSource
import com.android.diary.data.entity.MemoEntity
import com.android.diary.domain.model.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.utils.DEFAULT_PAGING_SIZE
import com.android.diary.domain.utils.mapPaging
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoRoomDataSource: MemoRoomDataSource
) : MemoRepository {
    override suspend fun upsert(memo: Memo) = memoRoomDataSource.upsert(MemoEntity(memo))

    override fun pagingAll() = Pager(
        config = PagingConfig(pageSize = DEFAULT_PAGING_SIZE),
        initialKey = null,
    ) {
        memoRoomDataSource.pagingAll()
    }.mapPaging(MemoEntity::toDomain)
}