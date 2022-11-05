package com.android.diary.data.repository

import com.android.diary.data.datasource.MemoRemoteDataSource
import com.android.diary.data.datasource.OAuthDataSource
import com.android.diary.domain.repository.BackupRepository
import javax.inject.Inject

class BackupRepositoryImpl @Inject constructor(
    private val oAuthDataSource: OAuthDataSource,
    private val memoRemoteDataSource: MemoRemoteDataSource
) : BackupRepository {
    override fun uploadData() = memoRemoteDataSource.upload()

    override fun downloadData() {
        TODO("Not yet implemented")
    }
}