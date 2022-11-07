package com.diary.android.presenter.memo.action

import java.util.UUID

sealed interface MemoListAction {
    data class NavigateToDetail(
        val id: String = UUID.randomUUID().toString(),
        val isNew: Boolean = true
    ) : MemoListAction
    data class Failure(val throwable: Throwable) : MemoListAction
}
