package com.diary.android.presenter.memo.screen

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.diary.share.show
import com.diary.android.presenter.memo.action.MemoDetailAction
import com.diary.android.presenter.memo.viewmodel.MemoDetailViewModel
import com.diary.android.presenter.ui.compose.memo.MemoDetailScreenCompose
import com.diary.android.share.StringResource
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MemoDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    memoDetailViewModel: MemoDetailViewModel = hiltViewModel()
) = MemoDetailScreenCompose(
    modifier = modifier,
    snackbarHostState = snackbarHostState,
    uiState = memoDetailViewModel.uiState.collectAsState().value,
).also {
    val context = LocalContext.current
    LaunchedEffect(memoDetailViewModel) {
        memoDetailViewModel.action.collectLatest {
            when (it) {
                is MemoDetailAction.NavigateUp -> navController.navigateUp()
                is MemoDetailAction.TitleEmpty -> snackbarHostState.show(
                    message = context.getString(StringResource.title_is_empty)
                )
                is MemoDetailAction.Add -> snackbarHostState.show(
                    message = it.title
                )
                is MemoDetailAction.Failure -> snackbarHostState.show(
                    context = context,
                    throwable = it.throwable
                )
            }
        }
    }
}
