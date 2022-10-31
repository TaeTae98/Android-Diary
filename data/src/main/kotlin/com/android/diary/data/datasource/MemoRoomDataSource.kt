package com.android.diary.data.datasource

import androidx.room.Dao
import com.android.diary.data.entity.MemoEntity
import com.android.diary.data.room.BaseDao

@Dao
interface MemoRoomDataSource : BaseDao<MemoEntity>