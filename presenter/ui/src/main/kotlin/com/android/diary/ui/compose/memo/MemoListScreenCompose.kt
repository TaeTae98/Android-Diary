package com.android.diary.ui.compose.memo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.ui.compose.core.button.AddFloatingButton
import com.android.diary.ui.theme.DiaryTheme3
import com.android.diary.ui.uistate.memo.MemoListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoListScreenCompose(
    modifier: Modifier = Modifier,
    uiState: MemoListUiState = MemoListUiState.List()
) = Scaffold(
    modifier = modifier,
    floatingActionButton = { FloatingButton(uiState = uiState) }
) {
    Content(modifier = Modifier.padding(it))
}

@Composable
private fun FloatingButton(
    modifier: Modifier = Modifier,
    uiState: MemoListUiState
) = when(uiState) {
    is MemoListUiState.List -> AddFloatingButton(
        modifier = modifier,
        onClick = uiState.onAdd
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier
) = LazyColumn(
    modifier = modifier
) {

}

@Composable
@Preview
private fun Preview() = DiaryTheme3 {
    MemoListScreenCompose()
}