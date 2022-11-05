package com.android.diary.ui.compose.more

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource
import com.android.diary.ui.compose.core.icon.AccountIcon
import com.android.diary.ui.compose.core.icon.BackupIcon
import com.android.diary.ui.uistate.more.MoreListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreListScreenCompose(
    modifier: Modifier = Modifier,
    uiState: MoreListUiState = MoreListUiState()
) = Scaffold(
    modifier = modifier,
    topBar = { TopBar() }
) {
    Content(
        modifier = Modifier.padding(it),
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier
) = TopAppBar(
    modifier = modifier,
    title = {
        Text(text = stringResource(id = StringResource.more))
    }
)

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    uiState: MoreListUiState,
) = Column(
    modifier = modifier
) {
    MoreText(
        icon = { AccountIcon() },
        text = stringResource(id = StringResource.account),
        onClick = uiState.onAccount
    )
    MoreText(
        icon = { BackupIcon()},
        text = stringResource(id = StringResource.backup),
        onClick = uiState.onBackup
    )
}

@Preview
@Composable
private fun Preview() = MoreListScreenCompose()