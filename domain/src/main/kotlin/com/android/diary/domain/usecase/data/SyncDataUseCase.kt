package com.android.diary.domain.usecase.data

import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.repository.UserSettingRepository
import com.android.diary.domain.usecase.core.SuspendUseCase
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class SyncDataUseCase @Inject constructor(
    private val userSettingRepository: UserSettingRepository,
    private val memoRepository: MemoRepository
) : SuspendUseCase<Unit, Unit>() {
    override suspend fun execute(account: DiaryAccount, parameter: Unit) {
        val userId = account.id ?: return
        syncMemo(userId)
    }

    private suspend fun syncMemo(userId: String) {
        while (true) {
            val updatedAt = userSettingRepository.getMemoLastUpdatedAt(userId)
                .firstOrNull()
                ?.takeIf { it != 0L }?.plus(1L) ?: 0L
            val data = memoRepository.findSyncData(userId = userId, updatedAt = updatedAt)
            val lastUpdatedAt = data.lastOrNull()?.updatedAt ?: break

            memoRepository.sync(memo = data, userId = userId)
            userSettingRepository.setMemoLastUpdatedAt(userId = userId, updatedAt = lastUpdatedAt)
        }
    }
}