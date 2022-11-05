package com.android.diary.data.datasource

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class MemoRemoteDataSource @Inject constructor(

) {
    fun upload() {
        Firebase.firestore.collection("memo").add("title" to "No Internet How123?").addOnSuccessListener {
            Log.d("PASSZ", "Success : $it")
        }.addOnFailureListener {
            Log.d("PASSZ", "Fail", it)
        }
    }
}