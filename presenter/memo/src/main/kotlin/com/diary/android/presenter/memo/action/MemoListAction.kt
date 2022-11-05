package com.diary.android.presenter.memo.action

sealed interface MemoListAction {
    data class NavigateToDetail(val id: Long) : MemoListAction
    data class Failure(val throwable: Throwable) : MemoListAction
}