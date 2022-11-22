package com.diary.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diary.android.domain.model.memo.Memo
import java.util.UUID

@Entity
data class MemoEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val userId: String? = null,
    val title: String = "",
    val description: String = "",
    val state: Int = 0,
    val beginDate: Int? = null,
    val endDate: Int? = null,
    val updatedAt: Long = System.currentTimeMillis(),
) {
    constructor(memo: Memo, userId: String?) : this(
        id = memo.id,
        userId = userId,
        title = memo.title,
        description = memo.description,
        beginDate = memo.beginDate,
        endDate = memo.endDate,
        updatedAt = memo.updatedAt
    )

    fun toDomain() = Memo(
        id = id,
        title = title,
        description = description,
        beginDate = beginDate,
        endDate = endDate,
        updatedAt = updatedAt
    )
}
