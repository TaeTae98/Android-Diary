package com.diary.android.share.oauth

import android.content.Intent
import androidx.activity.result.IntentSenderRequest

sealed interface GoogleOAuthRequest {
    data class OneTapSign(
        val intentSenderRequest: IntentSenderRequest
    ) : GoogleOAuthRequest

    data class Sign(
        val intent: Intent
    ) : GoogleOAuthRequest
}
