package com.android.diary.domain.usecase.backup

import com.android.diary.domain.repository.BackupRepository
import com.android.diary.domain.usecase.core.UseCase
import javax.inject.Inject

class DownloadDataUseCase @Inject constructor(
    private val backupRepository: BackupRepository
) : UseCase<Unit, Unit>() {
    override fun execute(parameter: Unit) = backupRepository.downloadData()
}