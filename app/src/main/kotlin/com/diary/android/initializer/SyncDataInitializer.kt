package com.diary.android.initializer

import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.startup.Initializer
import com.diary.android.di.InitializerEntryPoint
import com.diary.android.domain.usecase.account.GetDiaryAccountUseCase
import com.diary.android.domain.usecase.data.SyncDataUseCase
import com.diary.android.share.repeatOn
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

    override fun dependencies() = mutableListOf(TimberInitializer::class.java)
}
