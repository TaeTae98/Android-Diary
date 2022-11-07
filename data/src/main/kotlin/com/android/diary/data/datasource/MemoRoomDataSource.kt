package com.android.diary.data.datasource

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.android.diary.data.entity.MemoEntity
import com.android.diary.data.room.BaseDao
import com.android.diary.domain.utils.DEFAULT_PAGING_SIZE

@Dao
interface MemoRoomDataSource : BaseDao<MemoEntity> {
    @Query("SELECT * FROM MemoEntity WHERE id = :id")
    suspend fun findById(id: String): MemoEntity?

    @Query("UPDATE MemoEntity SET state = 'DELETED' WHERE id = :id")
    suspend fun deleteById(id: String): Int

    @Query("SELECT * FROM MemoEntity WHERE userId IS NULL LIMIT $DEFAULT_PAGING_SIZE")
    suspend fun findMigrationData(): List<MemoEntity>

    @Query("SELECT * FROM MemoEntity WHERE userId IS :userId AND state = 'NONE' ORDER BY updatedAt DESC")
    fun pagingByUserId(userId: String?): PagingSource<Int, MemoEntity>
}
