package com.diary.android.presenter.ui.compose.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource
import com.diary.android.presenter.ui.compose.core.icon.AccountIcon
import com.diary.android.presenter.ui.uistate.more.MoreListUiState

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
}

@Preview
@Composable
private fun Preview() = MoreListScreenCompose()
