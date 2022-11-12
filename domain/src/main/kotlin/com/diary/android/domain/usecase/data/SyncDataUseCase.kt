package com.diary.android.domain.usecase.data

import com.diary.android.domain.repository.MemoRepository
import com.diary.android.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Unit, Unit>() {
    override suspend fun execute(parameter: Unit) = memoRepository.sync()
}
