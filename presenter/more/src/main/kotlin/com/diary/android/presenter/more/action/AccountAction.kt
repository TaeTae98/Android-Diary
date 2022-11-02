package com.diary.android.presenter.more.action

import com.android.diary.share.oauth.GoogleOAuthRequest

sealed class AccountAction {
    object NavigateUp : AccountAction()

    data class GoogleSignIn(val request: GoogleOAuthRequest) : AccountAction()
    data class Failure(val throwable: Throwable) : AccountAction()
}