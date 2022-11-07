package com.android.diary.domain.usecase.account

import com.android.diary.domain.repository.OAuthRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : SuspendUseCase<Unit, Unit>() {
    override suspend fun execute(parameter: Unit) = oAuthRepository.signOut()
}
