package com.android.diary.ui.compose.memo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.ui.compose.component.ComponentHeader
import com.android.diary.ui.compose.core.button.AddFloatingButton
import com.android.diary.ui.compose.core.button.NavigateUpButton
import com.android.diary.ui.theme.DiaryDimen
import com.android.diary.ui.uistate.memo.MemoDetailUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailScreenCompose(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    uiState: MemoDetailUiState = MemoDetailUiState.Detail(),
) = Scaffold(
    modifier = modifier,
    topBar = { TopBar(onNavigateUp = uiState.onNavigateUp) },
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    floatingActionButton = { FloatingButton(uiState = uiState) }
) {
    Content(
        modifier = Modifier.padding(it),
        uiState = uiState
    )
}.also {
    BackHandler(onBack = uiState.onNavigateUp)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
) = TopAppBar(
    modifier = modifier,
    title = {

    },
    navigationIcon = {
        NavigateUpButton(onClick = onNavigateUp)
    }
)

@Composable
private fun FloatingButton(
    modifier: Modifier = Modifier,
    uiState: MemoDetailUiState
) = when (uiState) {
    is MemoDetailUiState.Add -> AddFloatingButton(
        modifier = modifier,
        onClick = uiState.onAdd
    )
    else -> Unit
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    uiState: MemoDetailUiState
) = Column(
    modifier = modifier.padding(
        horizontal = DiaryDimen.DEFAULT_HORIZONTAL,
        vertical = DiaryDimen.DEFAULT_VERTICAL
    ),
) {
    ComponentHeader(
        titleUiState = uiState.titleUiState,
        descriptionUiState = uiState.descriptionUiState
    )
}

@Composable
@Preview
private fun Preview() = MemoDetailScreenCompose()