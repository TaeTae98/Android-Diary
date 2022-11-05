package com.android.diary.data.datasource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.android.diary.data.entity.MemoEntity
import com.android.diary.data.room.BaseDao

@Dao
interface MemoRoomDataSource : BaseDao<MemoEntity> {
    @Query("SELECT * FROM MemoEntity WHERE id = :id")
    suspend fun findById(id: String): MemoEntity?

    @Query("DELETE FROM MemoEntity WHERE id = :id")
    suspend fun deleteById(id: String): Int

    @Query("SELECT * FROM MemoEntity")
    fun pagingAll(): PagingSource<Int, MemoEntity>
}