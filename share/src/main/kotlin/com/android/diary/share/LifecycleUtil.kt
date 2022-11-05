package com.android.diary.share

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.repeatOn(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend CoroutineScope.() -> Unit,
) = lifecycleScope.launch {
    repeatOnLifecycle(state, action)
}