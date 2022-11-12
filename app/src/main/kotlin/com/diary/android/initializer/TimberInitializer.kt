package com.diary.android.initializer

import android.content.Context
import androidx.startup.Initializer
import com.diary.android.BuildConfig
import com.diary.android.domain.utils.onTrue
import com.diary.android.timber.TimberTree
import timber.log.Timber

class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        BuildConfig.DEBUG.onTrue { Timber.plant(TimberTree) }
    }

    override fun dependencies() = mutableListOf<Class<out Initializer<*>>>()
}
