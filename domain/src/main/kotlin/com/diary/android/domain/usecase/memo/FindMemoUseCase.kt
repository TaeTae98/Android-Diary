package com.diary.android.domain.usecase.memo

import com.diary.android.domain.model.Id
import com.diary.android.domain.model.memo.Memo
import com.diary.android.domain.repository.MemoRepository
import com.diary.android.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class FindMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Id, Memo?>() {
    override suspend fun execute(parameter: Id) = memoRepository.findById(parameter.id)
}
