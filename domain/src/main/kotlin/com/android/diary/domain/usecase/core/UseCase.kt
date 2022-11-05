package com.android.diary.domain.usecase.core

abstract class UseCase<P, R> {
    operator fun invoke(parameter: P) = runCatching {
        execute(parameter)
    }

    protected abstract fun execute(parameter: P): R
}