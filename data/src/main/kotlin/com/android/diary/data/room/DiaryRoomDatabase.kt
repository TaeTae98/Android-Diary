package com.android.diary.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.diary.data.datasource.MemoRoomDataSource
import com.android.diary.data.entity.MemoEntity

@Database(
    entities = [MemoEntity::class],
    version = 1
)
abstract class DiaryRoomDatabase : RoomDatabase() {
    abstract fun memoRoomDataSource(): MemoRoomDataSource
}