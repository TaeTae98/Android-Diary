package com.android.diary.domain.usecase.account

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SignOutUseCase @Inject constructor() : SuspendUseCase<Unit, Unit>() {
    override suspend fun execute(
        account: DiaryAccount,
        parameter: Unit
    ) = oAuthRepository.signOut()
}