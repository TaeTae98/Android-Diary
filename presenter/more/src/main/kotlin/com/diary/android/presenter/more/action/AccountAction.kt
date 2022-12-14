package com.diary.android.presenter.more.action

import com.diary.android.share.oauth.GoogleOAuthRequest

sealed interface AccountAction {
    object NavigateUp : AccountAction
    object Migration : AccountAction

    data class GoogleSignIn(val request: GoogleOAuthRequest) : AccountAction
    data class Failure(val throwable: Throwable) : AccountAction
}
