package com.diary.android.presenter.more.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.diary.share.navigateToAccount
import com.android.diary.ui.compose.more.MoreListScreenCompose
import com.diary.android.presenter.more.action.MoreListAction
import com.diary.android.presenter.more.viewmodel.MoreListViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MoreListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    moreListViewModel: MoreListViewModel = hiltViewModel()
) = MoreListScreenCompose(
    modifier = modifier,
    uiState = moreListViewModel.uiState.collectAsState().value
).also {
    LaunchedEffect(moreListViewModel) {
        moreListViewModel.action.collectLatest {
            when(it) {
                MoreListAction.Account -> navController.navigateToAccount()
            }
        }
    }
}