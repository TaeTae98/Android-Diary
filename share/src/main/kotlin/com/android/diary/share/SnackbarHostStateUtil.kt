package com.android.diary.share

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SnackbarVisuals
import kotlinx.coroutines.withTimeout

suspend inline fun SnackbarHostState.show(
    message: String = "",
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration = SnackbarDuration.Short,
    action: () -> Unit = {},
) = when (
    showSnackbar(
        message = message,
        actionLabel = actionLabel,
        withDismissAction = withDismissAction,
        duration = duration
    )
) {
    SnackbarResult.ActionPerformed -> action()
    else -> Unit
}