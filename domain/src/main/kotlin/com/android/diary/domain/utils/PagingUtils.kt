package com.android.diary.domain.utils

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val DEFAULT_PAGING_SIZE = 25

fun <T : Any, R : Any> PagingData<T>.mapPaging(transform: suspend (T) -> R) = map(transform)

fun <T : Any, R : Any> Flow<PagingData<T>>.mapPaging(transform: suspend (T) -> R) = map {
    it.mapPaging(transform)
}

fun <T : Any, R : Any> Pager<*, T>.mapPaging(transform: suspend (T) -> R) = flow.mapPaging(transform)