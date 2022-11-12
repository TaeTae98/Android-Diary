package com.diary.android.domain.usecase.account

import com.diary.android.domain.model.IdToken
import com.diary.android.domain.repository.OAuthRepository
import com.diary.android.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : SuspendUseCase<IdToken, Unit>() {
    override suspend fun execute(parameter: IdToken) = oAuthRepository.signIn(parameter.token)
}
