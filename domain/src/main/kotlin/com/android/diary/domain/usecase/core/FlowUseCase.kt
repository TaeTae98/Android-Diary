package com.android.diary.domain.usecase.core

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

abstract class FlowUseCase<P, R> {
    @Inject
    lateinit var oAuthRepository: OAuthRepository

    operator fun invoke(parameter: P) = runCatching {
        oAuthRepository.getAccount().flatMapLatest {
            execute(it, parameter)
        }
    }

    protected abstract fun execute(account: DiaryAccount, parameter: P): Flow<R>
}