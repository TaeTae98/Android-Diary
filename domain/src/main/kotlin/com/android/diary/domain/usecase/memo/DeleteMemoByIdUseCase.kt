package com.android.diary.domain.usecase.memo

import com.android.diary.domain.model.Id
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class DeleteMemoByIdUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Id, Int>() {
    override suspend fun execute(parameter: Id) = memoRepository.deleteById(parameter.id)
}