package com.diary.android.presenter.more.action

sealed interface BackupAction {
    object NavigateUp : BackupAction
}