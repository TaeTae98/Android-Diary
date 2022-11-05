package com.diary.android.presenter.more.action

sealed interface MoreListAction {
    object Account : MoreListAction
    object Backup : MoreListAction
}