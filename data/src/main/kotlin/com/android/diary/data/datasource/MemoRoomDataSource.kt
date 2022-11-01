package com.android.diary.data.datasource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.android.diary.data.entity.MemoEntity
import com.android.diary.data.room.BaseDao

@Dao
interface MemoRoomDataSource : BaseDao<MemoEntity> {
    @Query("SELECT * FROM MemoEntity")
    fun pagingAll(): PagingSource<Int, MemoEntity>
}