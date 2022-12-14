package com.diary.android.presenter.ui.compose.core.button

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.presenter.ui.compose.core.icon.ClearIcon

@Composable
fun ClearButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true
) = IconButton(
    modifier = modifier,
    onClick = onClick,
    enabled = enabled
) {
    ClearIcon()
}

@Preview
@Composable
private fun Preview() = ClearButton()
