package com.android.diary.di

import com.android.diary.data.repository.OAuthRepositoryImpl
import com.android.diary.domain.repository.OAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositorySingletonBindsModule {
    @Binds
    @Singleton
    abstract fun bindsOAuthRepository(oAuthRepository: OAuthRepositoryImpl): OAuthRepository
}