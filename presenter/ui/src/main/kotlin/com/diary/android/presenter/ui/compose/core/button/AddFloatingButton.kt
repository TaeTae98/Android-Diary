package com.diary.android.presenter.ui.compose.core.button

import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.presenter.ui.compose.core.icon.AddIcon
import com.diary.android.presenter.ui.theme.DiaryTheme3

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
