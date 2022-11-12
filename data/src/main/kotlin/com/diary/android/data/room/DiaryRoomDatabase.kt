package com.diary.android.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.diary.android.data.datasource.MemoRoomDataSource
import com.diary.android.data.entity.MemoEntity

@Database(
    entities = [MemoEntity::class],
    version = 1
)
abstract class DiaryRoomDatabase : RoomDatabase() {
    abstract fun memoRoomDataSource(): MemoRoomDataSource
}
