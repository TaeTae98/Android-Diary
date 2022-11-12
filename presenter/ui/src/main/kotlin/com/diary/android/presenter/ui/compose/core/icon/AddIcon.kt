package com.diary.android.presenter.ui.compose.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.presenter.ui.theme.DiaryTheme3
import com.diary.android.share.StringResource

@Composable
fun AddIcon(
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = StringResource.add)
) = Icon(
    modifier = modifier,
    imageVector = Icons.Rounded.Add,
    contentDescription = contentDescription
)

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    AddIcon()
}
