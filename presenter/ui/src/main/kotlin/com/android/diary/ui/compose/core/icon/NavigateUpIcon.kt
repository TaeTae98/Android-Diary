package com.android.diary.ui.compose.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource
import com.android.diary.ui.theme.DiaryTheme3

@Composable
fun NavigateUpIcon(
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = StringResource.navigate_up)
) = Icon(
    modifier = modifier,
    imageVector = Icons.Rounded.ArrowBack,
    contentDescription = contentDescription
)

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    NavigateUpIcon()
}
