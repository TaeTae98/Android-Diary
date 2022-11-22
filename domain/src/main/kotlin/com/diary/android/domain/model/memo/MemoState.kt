package com.diary.android.domain.model.memo

import com.diary.android.domain.constant.Const

enum class MemoState(val value: Int) {
    NONE(Const.STATE_NONE), FINISHED(Const.STATE_FINISHED), DELETED(Const.STATE_DELETED);
}
