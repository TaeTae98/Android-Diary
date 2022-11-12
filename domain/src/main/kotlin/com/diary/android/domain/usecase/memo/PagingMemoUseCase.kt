package com.diary.android.domain.usecase.memo

import androidx.paging.PagingData
import com.diary.android.domain.model.memo.Memo
import com.diary.android.domain.repository.MemoRepository
import com.diary.android.domain.usecase.core.FlowUseCase
import javax.inject.Inject

class PagingMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) : FlowUseCase<Unit, PagingData<Memo>>() {
    override fun execute(parameter: Unit) = memoRepository.pagingAll()
}
