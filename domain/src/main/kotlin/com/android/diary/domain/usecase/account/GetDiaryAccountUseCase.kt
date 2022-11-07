package com.android.diary.domain.usecase.account

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.repository.OAuthRepository
import com.android.diary.domain.usecase.core.FlowUseCase
import javax.inject.Inject

class GetDiaryAccountUseCase @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : FlowUseCase<Unit, DiaryAccount>() {
    override fun execute(parameter: Unit) = oAuthRepository.getAccount()
}
