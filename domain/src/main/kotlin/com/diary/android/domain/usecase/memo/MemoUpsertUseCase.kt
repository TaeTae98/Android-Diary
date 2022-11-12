package com.diary.android.domain.usecase.memo

import com.diary.android.domain.model.memo.Memo
import com.diary.android.domain.repository.MemoRepository
import com.diary.android.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class MemoUpsertUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Memo, Unit>() {
    override suspend fun execute(parameter: Memo) = memoRepository.upsert(parameter)
}
