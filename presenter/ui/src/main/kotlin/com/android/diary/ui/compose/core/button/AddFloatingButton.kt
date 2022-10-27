package com.android.diary.ui.compose.core.button

import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.diary.ui.compose.core.icon.AddIcon

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