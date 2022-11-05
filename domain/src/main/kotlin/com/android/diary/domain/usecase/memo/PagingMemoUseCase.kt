package com.android.diary.domain.usecase.memo

import androidx.paging.PagingData
import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.model.Memo
import com.android.diary.domain.repository.MemoRepository
import com.android.diary.domain.usecase.core.FlowUseCase
import javax.inject.Inject

class PagingMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : FlowUseCase<Unit, PagingData<Memo>>() {
    override fun execute(
        account: DiaryAccount,
        parameter: Unit
    ) = memoRepository.pagingByUserId(account.id)
}