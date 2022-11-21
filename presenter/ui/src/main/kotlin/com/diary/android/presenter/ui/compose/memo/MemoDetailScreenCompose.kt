package com.diary.android.presenter.ui.compose.memo

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.presenter.ui.compose.component.ComponentDateRange
import com.diary.android.presenter.ui.compose.component.ComponentHeader
import com.diary.android.presenter.ui.compose.core.button.AddFloatingButton
import com.diary.android.presenter.ui.compose.core.button.NavigateUpButton
import com.diary.android.presenter.ui.theme.DiaryDimen
import com.diary.android.presenter.ui.uistate.memo.MemoDetailUiState

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
    modifier = modifier
        .padding(
            horizontal = DiaryDimen.DEFAULT_HORIZONTAL,
            vertical = DiaryDimen.DEFAULT_VERTICAL
        )
        .verticalScroll(
            state = rememberScrollState(),
            flingBehavior = ScrollableDefaults.flingBehavior()
        ),
) {
    ComponentHeader(
        titleUiState = uiState.titleUiState,
        descriptionUiState = uiState.descriptionUiState
    )

    ComponentDateRange(
        uiState = uiState.dateRangeUiState
    )
}

@Composable
@Preview
private fun Preview() = MemoDetailScreenCompose()
