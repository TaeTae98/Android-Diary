package com.android.diary.data.di

import com.android.diary.data.room.DiaryRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DataSourceViewModelProvidesModule {
    @Provides
    @ViewModelScoped
    fun providesMemoRoomDataSource(
        diaryRoomDatabase: DiaryRoomDatabase
    ) = diaryRoomDatabase.memoRoomDataSource()
}