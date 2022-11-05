package com.android.diary.domain.usecase.account

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.usecase.core.FlowUseCase
import javax.inject.Inject

class GetDiaryAccountUseCase @Inject constructor() : FlowUseCase<Unit, DiaryAccount>() {
    override fun execute(
        account: DiaryAccount,
        parameter: Unit
    ) = oAuthRepository.getAccount()
}