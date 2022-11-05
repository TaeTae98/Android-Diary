package com.android.diary.domain.usecase.account

import com.android.diary.domain.model.IdToken
import com.android.diary.domain.repository.OAuthRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : SuspendUseCase<IdToken, Unit>() {
    override suspend fun execute(
        parameter: IdToken
    ) = oAuthRepository.signIn(idToken = parameter.token)
}