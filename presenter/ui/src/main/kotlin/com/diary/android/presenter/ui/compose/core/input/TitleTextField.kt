package com.diary.android.presenter.ui.compose.core.input

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource
import com.diary.android.presenter.ui.theme.DiaryTheme3
import com.diary.android.presenter.ui.uistate.core.TextInputUiState

@Composable
fun TitleTextField(
    modifier: Modifier = Modifier,
    uiState: TextInputUiState = TextInputUiState(),
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) = ClearTextField(
    modifier = modifier,
    uiState = uiState,
    label = { Text(text = stringResource(id = StringResource.title)) },
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    singleLine = true
)

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    TitleTextField()
}
