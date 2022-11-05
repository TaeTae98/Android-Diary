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

    @Query("UPDATE MemoEntity SET userId = :userId WHERE userId IS NULL")
    suspend fun migration(userId: String?): Int

    @Query("SELECT * FROM MemoEntity WHERE userId IS :userId")
    fun pagingByUserId(userId: String?): PagingSource<Int, MemoEntity>
}