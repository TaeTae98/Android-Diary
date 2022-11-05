package com.android.diary.di

import android.content.Context
import com.android.diary.initializer.SyncDataInitializer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InitializerEntryPoint {
    fun inject(initializer: SyncDataInitializer)

    companion object {
        fun get(context: Context) = EntryPointAccessors.fromApplication<InitializerEntryPoint>(context)
    }
}