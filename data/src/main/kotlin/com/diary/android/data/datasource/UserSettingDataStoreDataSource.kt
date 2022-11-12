package com.diary.android.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserSettingDataStoreDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context
) {
    private val dataStoreMap = HashMap<String, DataStore<Preferences>>()

    fun getMemoLastUpdatedAt(userId: String?) = getDataStore(userId).data.map {
        it[MEMO_LAST_UPDATED_AT_KEY] ?: 0L
    }

    suspend fun setMemoLastUpdatedAt(userId: String?, updatedAt: Long) {
        getDataStore(userId).edit {
            it[MEMO_LAST_UPDATED_AT_KEY] = updatedAt
        }
    }

    private fun getDataStore(userId: String?) = dataStoreMap.getOrPut(
        key = userId ?: GUEST_USER_FILE_NAME
    ) {
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile(userId ?: GUEST_USER_FILE_NAME)
        }
    }

    companion object {
        private const val GUEST_USER_FILE_NAME = "guest"

        private val MEMO_LAST_UPDATED_AT_KEY = longPreferencesKey("memoLastUpdatedAtKey")
    }
}
