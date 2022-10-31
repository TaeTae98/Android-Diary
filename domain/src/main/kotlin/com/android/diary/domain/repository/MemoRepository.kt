package com.android.diary.domain.repository

import com.android.diary.domain.model.Memo

interface MemoRepository {
    suspend fun upsert(memo: Memo)
}