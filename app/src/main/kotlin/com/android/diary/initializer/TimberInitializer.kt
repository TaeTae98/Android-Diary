package com.android.diary.initializer

import android.content.Context
import androidx.startup.Initializer
import com.android.diary.BuildConfig
import com.android.diary.domain.utils.onTrue
import com.android.diary.timber.TimberTree
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        BuildConfig.DEBUG.onTrue { Timber.plant(TimberTree) }
    }

    override fun dependencies() = mutableListOf<Class<out Initializer<*>>>()
}
