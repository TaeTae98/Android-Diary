package com.diary.android.domain.usecase.memo

import com.diary.android.domain.model.Id
import com.diary.android.domain.repository.MemoRepository
import com.diary.android.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class DeleteMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Id, Unit>() {
    override suspend fun execute(parameter: Id) = memoRepository.deleteById(parameter.id)
}
