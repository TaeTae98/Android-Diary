package com.android.diary.domain.usecase.account

import com.android.diary.domain.model.AccountType
import com.android.diary.domain.repository.OAuthRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : SuspendUseCase<SignInUseCase.Params, Unit>() {
    data class Params(
        val idToken: String?,
        val type: AccountType
    )

    override suspend fun execute(
        parameter: Params
    ) = oAuthRepository.signIn(idToken = parameter.idToken, type = parameter.type)
}