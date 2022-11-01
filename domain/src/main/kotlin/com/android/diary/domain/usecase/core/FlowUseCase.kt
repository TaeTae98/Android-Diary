package com.android.diary.domain.usecase.core

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<P, R> {
    operator fun invoke(parameter: P) = runCatching {
        execute(parameter)
    }

    protected abstract fun execute(parameter: P): Flow<R>
}