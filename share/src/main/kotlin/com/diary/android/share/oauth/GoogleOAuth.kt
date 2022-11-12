package com.diary.android.share.oauth

import android.content.Context
import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import com.diary.android.share.BuildConfig
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class GoogleOAuth @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val oneTapClient = Identity.getSignInClient(context)

    suspend fun signIn() = runCatching {
        try {
            oneTapSignIn()
        } catch (e: Exception) {
            if (e !is ApiException || e.statusCode != CREDENTIAL_NOT_FOUND) {
                Timber.e(e, "Google OneTap SignIn Failure")
            }
            googleSignIn()
        }
    }

    private suspend fun oneTapSignIn(): GoogleOAuthRequest {
        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(BuildConfig.GOOGLE_OAUTH_CLIENT_ID)
                    .build()
            ).build()

        val signInResult = oneTapClient.beginSignIn(signInRequest).await()
        val intentSenderRequest = IntentSenderRequest.Builder(signInResult.pendingIntent).build()

        return GoogleOAuthRequest.OneTapSign(intentSenderRequest)
    }

    private fun googleSignIn(): GoogleOAuthRequest {
        val options = GoogleSignInOptions.Builder()
            .requestIdToken(BuildConfig.GOOGLE_OAUTH_CLIENT_ID)
            .requestEmail()
            .requestProfile()
            .build()

        return GoogleOAuthRequest.Sign(GoogleSignIn.getClient(context, options).signInIntent)
    }

    suspend fun getIdToken(result: GoogleOAuthResult) = runCatching {
        when (result) {
            is GoogleOAuthResult.OneTapSign -> getIdTokenByOneTapSign(result.intent)
            is GoogleOAuthResult.Sign -> getIdTokenBySign(result.intent)
        }
    }

    private fun getIdTokenByOneTapSign(
        intent: Intent?
    ) = oneTapClient.getSignInCredentialFromIntent(intent).googleIdToken

    private suspend fun getIdTokenBySign(
        intent: Intent?
    ) = GoogleSignIn.getSignedInAccountFromIntent(intent).await().idToken

    companion object {
        private const val CREDENTIAL_NOT_FOUND = 16
    }
}
