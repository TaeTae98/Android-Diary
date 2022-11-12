package com.diary.android.data.repository

import com.diary.android.data.datasource.OAuthDataSource
import com.diary.android.data.entity.DiaryAccountEntity
import com.diary.android.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthRepositoryImpl @Inject constructor(
    private val oAuthDataSource: OAuthDataSource,
) : OAuthRepository {
    override fun getAccount() = oAuthDataSource.user.map(DiaryAccountEntity::toDomain)

    override suspend fun signIn(idToken: String?) = oAuthDataSource.signIn(idToken = idToken)

    override suspend fun signOut() = oAuthDataSource.signOut()
}
