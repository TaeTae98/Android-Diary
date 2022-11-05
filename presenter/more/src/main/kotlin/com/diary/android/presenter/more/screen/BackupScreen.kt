package com.diary.android.presenter.more.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.diary.ui.compose.backup.BackupScreenCompose
import com.diary.android.presenter.more.action.BackupAction
import com.diary.android.presenter.more.viewmodel.BackupViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BackupScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    backupViewModel: BackupViewModel = hiltViewModel()
) = BackupScreenCompose(
    modifier = modifier,
    uiState = backupViewModel.uiState.collectAsState().value
).also {
    LaunchedEffect(backupViewModel) {
        backupViewModel.action.collectLatest {
            when (it) {
                is BackupAction.NavigateUp -> navController.navigateUp()
            }
        }
    }
}