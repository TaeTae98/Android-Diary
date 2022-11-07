package com.android.diary.domain.usecase.memo

import com.android.diary.domain.model.memo.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class MemoUpsertUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Memo, Unit>() {
    override suspend fun execute(parameter: Memo) = memoRepository.upsert(parameter)
}
