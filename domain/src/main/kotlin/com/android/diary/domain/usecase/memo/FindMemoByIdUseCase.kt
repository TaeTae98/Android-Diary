package com.android.diary.domain.usecase.memo

import com.android.diary.domain.model.Id
import com.android.diary.domain.model.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class FindMemoByIdUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Id, Memo?>() {
    override suspend fun execute(parameter: Id) = memoRepository.findById(parameter.id)
}