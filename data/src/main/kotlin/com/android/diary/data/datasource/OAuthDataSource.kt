package com.android.diary.data.datasource

import com.android.diary.data.entity.DiaryAccountEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthDataSource @Inject constructor() {
    private val _user = MutableStateFlow(
        DiaryAccountEntity.from(FirebaseAuth.getInstance().currentUser)
    )

    val user = _user.asStateFlow()

    suspend fun signIn(idToken: String?) {
        val result = FirebaseAuth.getInstance().signInWithCredential(
            GoogleAuthProvider.getCredential(idToken, null)
        ).await()

        _user.emit(DiaryAccountEntity.from(result.user))
    }

    suspend fun signOut() = FirebaseAuth.getInstance().signOut().also {
        _user.emit(DiaryAccountEntity.Guest)
    }
}
