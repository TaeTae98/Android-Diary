package com.diary.android.presenter.memo.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.android.diary.share.navigateToMemoDetail
import com.android.diary.share.show
import com.android.diary.ui.compose.memo.MemoListScreenCompose
import com.diary.android.presenter.memo.action.MemoListAction
import com.diary.android.presenter.memo.viewmodel.MemoListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MemoListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    memoListViewModel: MemoListViewModel = hiltViewModel()
) = MemoListScreenCompose(
    modifier = modifier,
    snackbarHostState = snackbarHostState,
    uiState = memoListViewModel.uiState,
    memoItems = memoListViewModel.memoUiState.collectAsLazyPagingItems()
).also {
    val context = LocalContext.current
    LaunchedEffect(memoListViewModel) {
        memoListViewModel.action.collectLatest {
            when (it) {
                is MemoListAction.NavigateToDetail -> navController.navigateToMemoDetail(
                    id = it.id,
                    isNew = it.isNew
                )
                is MemoListAction.Failure -> snackbarHostState.show(
                    context = context,
                    throwable = it.throwable
                )
            }
        }
    }
}
