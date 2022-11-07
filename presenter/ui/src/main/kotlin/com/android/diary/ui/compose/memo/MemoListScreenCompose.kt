package com.android.diary.ui.compose.memo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.android.diary.domain.utils.isNotNull
import com.android.diary.ui.compose.core.button.AddFloatingButton
import com.android.diary.ui.compose.modifer.swipeToDismiss
import com.android.diary.ui.theme.DiaryDimen
import com.android.diary.ui.theme.DiaryTheme3
import com.android.diary.ui.uistate.memo.MemoListUiState
import com.android.diary.ui.uistate.memo.MemoUiState
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoListScreenCompose(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    uiState: MemoListUiState = MemoListUiState.List(),
    memoItems: LazyPagingItems<out MemoUiState> = flowOf(
        PagingData.from(listOf(MemoUiState.Simple(title = "Memo")))
    ).collectAsLazyPagingItems(),
) = Scaffold(
    modifier = modifier,
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    floatingActionButton = { FloatingButton(uiState = uiState) }
) {
    Content(
        modifier = Modifier.padding(it),
        memoItems = memoItems
    )
}

@Composable
private fun FloatingButton(
    modifier: Modifier = Modifier,
    uiState: MemoListUiState
) = when (uiState) {
    is MemoListUiState.List -> AddFloatingButton(
        modifier = modifier,
        onClick = uiState.onAdd
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Content(
    modifier: Modifier = Modifier,
    memoItems: LazyPagingItems<out MemoUiState>
) = LazyColumn(
    modifier = modifier.fillMaxSize(),
    contentPadding = PaddingValues(
        horizontal = DiaryDimen.DEFAULT_HORIZONTAL,
        vertical = DiaryDimen.DEFAULT_VERTICAL
    ),
    verticalArrangement = Arrangement.spacedBy(2.dp)
) {
    items(
        items = memoItems,
        key = { it.id }
    ) {
        val simple = (it as? MemoUiState.Simple)

        MemoCompose(
            modifier = Modifier.swipeToDismiss(
                enable = simple.isNotNull(),
                onDismissed = simple?.onDelete
            ).animateItemPlacement(),
            uiState = it,
        )
    }
}

@Composable
@Preview
private fun Preview() = DiaryTheme3 {
    MemoListScreenCompose()
}
