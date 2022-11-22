package com.diary.android.data.datasource

import com.diary.android.data.entity.MemoEntity
import com.diary.android.domain.constant.Parameter
import com.diary.android.domain.model.memo.MemoState
import com.diary.android.domain.utils.DEFAULT_PAGING_SIZE
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MemoRemoteDataSource @Inject constructor() {
    private val database: FirebaseFirestore
        get() = Firebase.firestore

    fun upsert(entity: MemoEntity) = database.collection(COLLECTION)
        .document(entity.id)
        .set(entity)

    fun upsert(entities: Collection<MemoEntity>) = entities.forEach(::upsert)

    fun deleteById(id: String) = database.collection(COLLECTION)
        .document(id)
        .update(Parameter.STATE, MemoState.DELETED.value)

    suspend fun findSyncData(
        userId: String,
        updatedAt: Long
    ): List<MemoEntity> = withContext(Dispatchers.IO) {
        database.collection(COLLECTION)
            .whereEqualTo(Parameter.USER_ID, userId)
            .whereGreaterThanOrEqualTo(Parameter.UPDATED_AT, updatedAt)
            .orderBy(Parameter.UPDATED_AT)
            .limit(DEFAULT_PAGING_SIZE * 2L)
            .get()
            .await()
            .toObjects(MemoEntity::class.java)
    }

    companion object {
        private const val COLLECTION = "memo"
    }
}
