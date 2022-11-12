package com.diary.android.data.di

import com.diary.android.data.room.DiaryRoomDatabase
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
