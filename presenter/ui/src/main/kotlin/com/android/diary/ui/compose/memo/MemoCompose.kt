package com.android.diary.ui.compose.memo

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.ui.uistate.memo.MemoUiState

@Composable
fun MemoCompose(
    modifier: Modifier = Modifier,
    uiState: MemoUiState? = null
) = when (uiState) {
    is MemoUiState.Simple -> SimpleMemo(
        modifier = modifier,
        uiState = uiState
    )
    else -> Unit
}

@Composable
private fun SimpleMemo(
    modifier: Modifier = Modifier,
    uiState: MemoUiState.Simple
) = Card(
    modifier = modifier
) {
    Box(contentAlignment = Alignment.Center) {
        Text(text = uiState.title, maxLines = 1)
    }
}

@Preview
@Composable
private fun SimpleMemoPreview() = SimpleMemo(
    uiState = MemoUiState.Simple(title = "Memo")
)