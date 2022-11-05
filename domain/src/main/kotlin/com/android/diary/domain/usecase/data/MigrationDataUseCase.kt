package com.android.diary.domain.usecase.data

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class MigrationDataUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : SuspendUseCase<Unit, Unit>() {
    override suspend fun execute(
        account: DiaryAccount,
        parameter: Unit
    ) {
        memoRepository.migration(account.id)
    }
}