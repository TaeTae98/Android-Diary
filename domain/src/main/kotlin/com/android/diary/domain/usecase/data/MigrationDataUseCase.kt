package com.android.diary.domain.usecase.data

import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class MigrationDataUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Unit, Unit>() {
    override suspend fun execute(parameter: Unit) = memoRepository.migration()
}
