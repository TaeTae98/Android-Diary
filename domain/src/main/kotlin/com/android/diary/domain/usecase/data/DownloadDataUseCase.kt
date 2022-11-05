package com.android.diary.domain.usecase.data

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class DownloadDataUseCase @Inject constructor(
) : SuspendUseCase<Unit, Unit>(){
    override suspend fun execute(account: DiaryAccount, parameter: Unit) {
        TODO("Not yet implemented")
    }
}