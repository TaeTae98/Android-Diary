package com.android.diary.ui.compose.core.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.ui.compose.core.button.ClearButton
import com.android.diary.ui.theme.DiaryTheme3
import com.android.diary.ui.uistate.core.TextInputUiState

@Composable
fun ClearTextField(
    modifier: Modifier = Modifier,
    uiState: TextInputUiState = TextInputUiState(),
    focusRequester: FocusRequester = remember { FocusRequester() },
    label: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
) = DiaryTextField(
    modifier = modifier
        .fillMaxWidth()
        .focusRequester(focusRequester),
    uiState = uiState,
    focusRequester = focusRequester,
    label = label,
    trailingIcon = {
        ClearButton(
            onClick = remember {
                {
                    uiState.onValueChange("")
                    focusRequester.requestFocus()
                }
            },
            enabled = uiState.value.isNotEmpty()
        )
    },
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = singleLine,
    maxLines = maxLines
)

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    ClearTextField()
}