package com.android.diary.domain.usecase

import com.android.diary.domain.model.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class MemoUpsertUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Memo, Unit>() {
    override suspend fun execute(parameter: Memo) = memoRepository.upsert(parameter)
}