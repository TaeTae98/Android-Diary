package com.android.diary.share

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult

suspend inline fun SnackbarHostState.show(
    message: String,
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

suspend inline fun SnackbarHostState.show(
    context: Context,
    throwable: Throwable,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration = SnackbarDuration.Short,
    action: () -> Unit = {},
) = show(
    message = throwable.message ?: context.getString(StringResource.default_error_message),
    actionLabel = actionLabel,
    withDismissAction = withDismissAction,
    duration = duration,
    action = action
)
