package com.android.diary.domain.usecase.memo

import com.android.diary.domain.model.Id
import com.android.diary.domain.model.memo.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class FindMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Id, Memo?>() {
    override suspend fun execute(parameter: Id) = memoRepository.findById(parameter.id)
}
