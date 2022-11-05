package com.android.diary.domain.usecase.core

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.repository.OAuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

abstract class SuspendUseCase<P, R> {
    @Inject
    lateinit var oAuthRepository: OAuthRepository

    suspend operator fun invoke(parameter: P) = runCatching {
        val account = oAuthRepository.getAccount().first()
        execute(account = account, parameter = parameter)
    }

    protected abstract suspend fun execute(account: DiaryAccount, parameter: P): R
}