package com.diary.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.diary.android.domain.model.memo.Memo
import com.diary.android.domain.model.memo.MemoState
import java.util.UUID

@Entity
data class MemoEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val userId: String? = null,
    val title: String = "",
    val description: String = "",
    val state: MemoState = MemoState.NONE,
    val updatedAt: Long = System.currentTimeMillis(),
) {
    constructor(memo: Memo, userId: String?) : this(
        id = memo.id,
        userId = userId,
        title = memo.title,
        description = memo.description,
        updatedAt = memo.updatedAt
    )

    fun toDomain() = Memo(
        id = id,
        title = title,
        description = description,
        updatedAt = updatedAt
    )
}
