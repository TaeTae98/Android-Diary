package com.diary.android.domain.usecase.account

import com.diary.android.domain.repository.OAuthRepository
import com.diary.android.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val oAuthRepository: OAuthRepository
) : SuspendUseCase<Unit, Unit>() {
    override suspend fun execute(parameter: Unit) = oAuthRepository.signOut()
}
