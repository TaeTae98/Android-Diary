package com.android.diary.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.diary.domain.model.Memo
import java.util.*

@Entity
data class MemoEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val userId: String? = null,
    val title: String = "",
    val description: String = "",
    val updatedAt: Long = System.currentTimeMillis(),
) {
    constructor(memo: Memo, userId: String?) : this(
        id = memo.id,
        userId = userId,
        title = memo.title,
        description = memo.description
    )

    fun toDomain() = Memo(
        id = id,
        title = title,
        description = description
    )
}