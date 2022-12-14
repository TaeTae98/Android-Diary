package com.diary.android.presenter.ui.compose.memo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diary.android.presenter.ui.uistate.memo.MemoUiState

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
    modifier = modifier,
    onClick = uiState.onClick
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = uiState.title,
            maxLines = 1
        )
    }
}

@Preview(widthDp = 200)
@Composable
private fun SimpleMemoPreview() = SimpleMemo(
    uiState = MemoUiState.Simple(title = "Memo")
)
