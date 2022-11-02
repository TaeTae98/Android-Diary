package com.android.diary.share.oauth

import android.content.Intent
import androidx.activity.result.IntentSenderRequest

sealed class GoogleOAuthRequest {
    data class OneTapSign(
        val intentSenderRequest: IntentSenderRequest
    ) : GoogleOAuthRequest()

    data class Sign(
        val intent: Intent
    ) : GoogleOAuthRequest()
}