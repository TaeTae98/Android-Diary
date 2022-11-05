package com.android.diary.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.diary.domain.model.Memo
import java.util.*

@Entity
data class MemoEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val description: String = "",
) {
    constructor(memo: Memo) : this(
        id = memo.id,
        title = memo.title,
        description = memo.description
    )

    fun toDomain() = Memo(
        id = id,
        title = title,
        description = description
    )
}