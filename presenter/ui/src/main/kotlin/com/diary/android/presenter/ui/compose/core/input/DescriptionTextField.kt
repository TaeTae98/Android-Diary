package com.diary.android.presenter.ui.compose.core.input

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource
import com.diary.android.presenter.ui.theme.DiaryTheme3
import com.diary.android.presenter.ui.uistate.core.TextInputUiState

@Composable
fun DescriptionTextField(
    modifier: Modifier = Modifier,
    uiState: TextInputUiState = TextInputUiState(),
    maxLines: Int = 10,
) = ClearTextField(
    modifier = modifier,
    uiState = uiState,
    label = { Text(text = stringResource(id = StringResource.description)) },
    maxLines = maxLines
)

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    DescriptionTextField()
}
