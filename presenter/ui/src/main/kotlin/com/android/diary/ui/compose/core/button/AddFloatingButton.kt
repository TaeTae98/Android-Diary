package com.android.diary.ui.compose.core.button

import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.ui.compose.core.icon.AddIcon
import com.android.diary.ui.theme.DiaryTheme3

@Composable
fun AddFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) = FloatingActionButton(
    modifier = modifier,
    onClick = onClick
) {
    AddIcon()
}

@Preview
@Composable
private fun Preview() = DiaryTheme3 {
    AddFloatingButton()
}
