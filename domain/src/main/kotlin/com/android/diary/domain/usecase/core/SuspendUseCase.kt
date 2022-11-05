package com.android.diary.domain.usecase.core

abstract class SuspendUseCase<P, R> {
    suspend operator fun invoke(parameter: P) = runCatching {
        execute(parameter)
    }

    protected abstract suspend fun execute(parameter: P): R
}