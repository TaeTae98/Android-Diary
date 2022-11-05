package com.android.diary.di

import com.android.diary.data.repository.BackupRepositoryImpl
import com.android.diary.data.repository.MemoRepositoryImpl
import com.android.diary.data.repository.OAuthRepositoryImpl
import com.android.diary.domain.repository.BackupRepository
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.repository.OAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryViewModelBindsModule {
    @Binds
    @ViewModelScoped
    abstract fun bindsMemoRepository(repository: MemoRepositoryImpl): MemoRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsOAuthRepository(oAuthRepository: OAuthRepositoryImpl): OAuthRepository

    @Binds
    @ViewModelScoped
    abstract fun bindsBackupRepository(repository: BackupRepositoryImpl): BackupRepository
}