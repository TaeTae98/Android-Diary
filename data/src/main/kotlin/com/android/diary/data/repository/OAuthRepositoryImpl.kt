package com.android.diary.data.repository

import com.android.diary.domain.model.AccountType
import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.repository.OAuthRepository
import com.android.diary.domain.utils.isNotNull
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthRepositoryImpl @Inject constructor(

) : OAuthRepository {
    private val user = MutableStateFlow(FirebaseAuth.getInstance().currentUser)

    override fun getAccount() = user.map {
        if (it.isNotNull()) {
            DiaryAccount.User(name = it.displayName, email = it.email)
        } else {
            DiaryAccount.Guest
        }
    }

    override suspend fun signIn(idToken: String?, type: AccountType) {
        val result = FirebaseAuth.getInstance().signInWithCredential(
            GoogleAuthProvider.getCredential(idToken, null)
        ).await()

        user.emit(result.user)
    }

    override suspend fun signOut() = user.emit(null)
}