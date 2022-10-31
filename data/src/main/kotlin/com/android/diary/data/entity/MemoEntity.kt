package com.android.diary.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.diary.domain.model.Memo

@Entity
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String = "",
    val description: String = "",
) {
    constructor(memo: Memo) : this(
        id = memo.id,
        title = memo.title,
        description = memo.description
    )
}