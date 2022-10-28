package com.android.diary.ui.compose.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource
import com.android.diary.ui.theme.DiaryTheme3

@Composable
fun ClearIcon(
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = StringResource.clear)
) = Icon(
    modifier = modifier,
    imageVector = Icons.Rounded.Clear,
    contentDescription = contentDescription
)

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    ClearIcon()
}