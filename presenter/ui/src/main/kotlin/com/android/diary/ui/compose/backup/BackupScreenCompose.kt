package com.android.diary.ui.compose.backup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.diary.share.StringResource
import com.android.diary.ui.compose.core.button.NavigateUpButton
import com.android.diary.ui.compose.core.icon.DownloadIcon
import com.android.diary.ui.compose.core.icon.UploadIcon
import com.android.diary.ui.compose.more.MoreText
import com.android.diary.ui.uistate.backup.BackupUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackupScreenCompose(
    modifier: Modifier = Modifier,
    uiState: BackupUiState = BackupUiState()
) = Scaffold(
    modifier = modifier,
    topBar = { TopBar(onNavigateUp = uiState.onNavigateUp) }
) {
    Content(
        modifier = Modifier.padding(it),
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit
) = TopAppBar(
    modifier = modifier,
    title = { Text(text = stringResource(id = StringResource.backup)) },
    navigationIcon = { NavigateUpButton(onClick = onNavigateUp) }
)

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    uiState: BackupUiState
) = Column(
    modifier = modifier,
) {
    MoreText(
        icon = { UploadIcon() },
        text = stringResource(id = StringResource.upload),
        onClick = uiState.onUpload
    )
    MoreText(
        icon = { DownloadIcon() },
        text = stringResource(id = StringResource.download),
        onClick = uiState.onDownload
    )
}