package com.android.diary.domain.usecase.account

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.model.IdToken
import com.android.diary.domain.usecase.core.SuspendUseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor() : SuspendUseCase<IdToken, Unit>() {
    override suspend fun execute(
        account: DiaryAccount,
        parameter: IdToken
    ) = oAuthRepository.signIn(idToken = parameter.token)
}