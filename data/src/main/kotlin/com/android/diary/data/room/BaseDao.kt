package com.android.diary.data.room

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface BaseDao<T : Any> {
    @Upsert
    suspend fun upsert(entity: T)

    @Upsert
    suspend fun upsert(entities: Collection<T>)
}
