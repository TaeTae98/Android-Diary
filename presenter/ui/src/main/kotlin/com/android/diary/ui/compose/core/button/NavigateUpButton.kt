package com.android.diary.ui.compose.core.button

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.ui.compose.core.icon.NavigateUpIcon
import com.android.diary.ui.theme.DiaryTheme3

@Composable
fun NavigateUpButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) = IconButton(
    modifier = modifier,
    onClick = onClick
) {
    NavigateUpIcon()
}

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    NavigateUpButton()
}
