package com.diary.android.domain.usecase.account

import com.diary.android.domain.model.DiaryAccount
import com.diary.android.domain.repository.OAuthRepository
import com.diary.android.domain.usecase.core.FlowUseCase
import javax.inject.Inject

class GetDiaryAccountUseCase @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : FlowUseCase<Unit, DiaryAccount>() {
    override fun execute(parameter: Unit) = oAuthRepository.getAccount()
}
