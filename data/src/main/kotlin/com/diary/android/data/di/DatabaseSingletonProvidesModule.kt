package com.diary.android.data.di

import android.content.Context
import androidx.room.Room
import com.android.diary.data.BuildConfig
import com.diary.android.data.room.DiaryRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseSingletonProvidesModule {
    @Provides
    @Singleton
    fun providesDiaryRoomDatabase(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context = context,
        klass = DiaryRoomDatabase::class.java,
        name = BuildConfig.DIARY_ROOM_DATABASE_NAME
    ).build()
}
