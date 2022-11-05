package com.diary.android.presenter.memo.action

sealed interface MemoDetailAction {
    object NavigateUp : MemoDetailAction
    object TitleEmpty : MemoDetailAction

    data class Add(val title: String) : MemoDetailAction
    data class Failure(val throwable: Throwable) : MemoDetailAction
}