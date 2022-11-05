package com.android.diary.data.di

import com.android.diary.data.room.DiaryRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceSingletonProvidesModule {
    @Provides
    @Singleton
    fun providesMemoRoomDataSource(
        diaryRoomDatabase: DiaryRoomDatabase
    ) = diaryRoomDatabase.memoRoomDataSource()
}