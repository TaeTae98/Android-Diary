package com.diary.android.presenter.ui.compose.core.input

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.domain.utils.isNotNull
import com.diary.android.domain.utils.onTrue
import com.diary.android.presenter.ui.uistate.core.TextInputUiState

@Composable
fun DiaryTextField(
    modifier: Modifier = Modifier,
    uiState: TextInputUiState = TextInputUiState(),
    focusRequester: FocusRequester = remember { FocusRequester() },
    label: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) = TextField(
    modifier = modifier.focusRequester(focusRequester),
    value = uiState.value,
    onValueChange = uiState.onValueChange,
    label = label,
    trailingIcon = trailingIcon,
    isError = uiState.errorAt.isNotNull(),
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    maxLines = maxLines
).also {
    LaunchedEffect(uiState.errorAt) {
        uiState.errorAt.isNotNull().onTrue { focusRequester.requestFocus() }
    }
}

@Preview
@Composable
private fun Preview() = DiaryTextField()
