package com.android.diary.data.datasource

import com.android.diary.data.entity.MemoEntity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class MemoRemoteDataSource @Inject constructor(

) {
    private val database: FirebaseFirestore
        get() = Firebase.firestore

    fun upsert(entity: MemoEntity) = database.collection(COLLECTION)
        .document(entity.id)
        .set(entity)

    companion object {
        private const val COLLECTION = "memo"
    }
}