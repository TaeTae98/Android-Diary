package com.diary.android.presenter.ui.compose.memo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.diary.android.domain.utils.isNotNull
import com.diary.android.presenter.ui.compose.core.button.AddFloatingButton
import com.diary.android.presenter.ui.compose.modifer.swipeToDismiss
import com.diary.android.presenter.ui.theme.DiaryDimen
import com.diary.android.presenter.ui.uistate.memo.MemoListUiState
import com.diary.android.presenter.ui.uistate.memo.MemoUiState
import kotlinx.coroutines.flow.flowOf

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
    floatingActionButton = {
        when (uiState) {
            is MemoListUiState.List -> AddFloatingButton(
                modifier = modifier,
                onClick = uiState.onAdd
            )
        }
    }
) {
    Content(
        modifier = Modifier.padding(it),
        memoItems = memoItems
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
private fun Preview() = MemoListScreenCompose()
