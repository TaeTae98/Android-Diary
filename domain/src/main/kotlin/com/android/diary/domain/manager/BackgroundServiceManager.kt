package com.android.diary.domain.manager

interface BackgroundServiceManager {
    suspend fun downloadData(userId: String)
}