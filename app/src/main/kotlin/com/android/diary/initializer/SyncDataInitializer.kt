package com.android.diary.initializer

import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.startup.Initializer
import com.android.diary.di.InitializerEntryPoint
import com.android.diary.domain.usecase.account.GetDiaryAccountUseCase
import com.android.diary.domain.usecase.data.SyncDataUseCase
import com.android.diary.share.repeatOn
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class SyncDataInitializer : Initializer<Unit> {
    @Inject
    lateinit var accountUseCase: GetDiaryAccountUseCase

    @Inject
    lateinit var syncDataUseCase: SyncDataUseCase

    override fun create(context: Context) {
        InitializerEntryPoint.get(context).inject(this)
        ProcessLifecycleOwner.get().repeatOn { init() }
    }

    private suspend fun init() {
        accountUseCase(Unit).onSuccess {
            it.collectLatest { syncDataUseCase(Unit) }
        }
    }

    override fun dependencies() = mutableListOf<Class<out Initializer<*>>>()
}