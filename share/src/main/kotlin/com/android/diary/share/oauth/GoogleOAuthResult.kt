package com.android.diary.share.oauth

import android.content.Intent

sealed class GoogleOAuthResult {
    data class OneTapSign(
        val intent: Intent?
    ) : GoogleOAuthResult()

    data class Sign(
        val intent: Intent?
    ) : GoogleOAuthResult()
}