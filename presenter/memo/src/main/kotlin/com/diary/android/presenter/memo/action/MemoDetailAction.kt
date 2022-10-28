package com.diary.android.presenter.memo.action

sealed class MemoDetailAction {
    object NavigateUp : MemoDetailAction()
    object TitleEmpty : MemoDetailAction()
}