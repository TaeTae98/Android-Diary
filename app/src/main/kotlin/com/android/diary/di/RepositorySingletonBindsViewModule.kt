package com.android.diary.di

import com.android.diary.data.repository.MemoRepositoryImpl
import com.android.diary.data.repository.OAuthRepositoryImpl
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.repository.OAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositorySingletonBindsViewModule {
    @Binds
    @Singleton
    abstract fun bindsMemoRepository(repository: MemoRepositoryImpl): MemoRepository

    @Binds
    @Singleton
    abstract fun bindsOAuthRepository(repository: OAuthRepositoryImpl): OAuthRepository
}
