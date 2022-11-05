package com.android.diary.data.repository

import com.android.diary.data.datasource.OAuthDataSource
import com.android.diary.data.entity.DiaryAccountEntity
import com.android.diary.domain.repository.OAuthRepository
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