package com.diary.android.presenter.memo.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.diary.share.navigateToMemoDetail
import com.android.diary.ui.compose.memo.MemoListScreenCompose
import com.diary.android.presenter.memo.action.MemoListAction
import com.diary.android.presenter.memo.viewmodel.MemoListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MemoListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    memoListViewModel: MemoListViewModel = hiltViewModel()
) = MemoListScreenCompose(
    modifier = modifier,
    uiState = memoListViewModel.uiState
).also {
    LaunchedEffect(memoListViewModel) {
        memoListViewModel.action.collectLatest {
            when(it) {
                is MemoListAction.Add -> navController.navigateToMemoDetail()
            }
        }
    }
}