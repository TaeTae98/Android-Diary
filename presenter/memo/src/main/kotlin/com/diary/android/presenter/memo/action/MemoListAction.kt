package com.diary.android.presenter.memo.action

sealed class MemoListAction {
    object Add : MemoListAction()

    data class Failure(val throwable: Throwable) : MemoListAction()
}