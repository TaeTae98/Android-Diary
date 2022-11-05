package com.android.diary.domain.repository

interface BackupRepository {
    fun uploadData()
    fun downloadData()
}