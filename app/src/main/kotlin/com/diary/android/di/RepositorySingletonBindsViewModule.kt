package com.diary.android.di

import com.diary.android.data.repository.MemoRepositoryImpl
import com.diary.android.data.repository.OAuthRepositoryImpl
import com.diary.android.domain.repository.MemoRepository
import com.diary.android.domain.repository.OAuthRepository
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
