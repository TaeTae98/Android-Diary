package com.android.diary.ui.compose.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.diary.share.StringResource
import com.android.diary.ui.uistate.more.MoreListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreListScreenCompose(
    modifier: Modifier = Modifier,
    uiState: MoreListUiState = MoreListUiState()
) = Scaffold(
    modifier = modifier
) {
    Content(
        modifier = Modifier.padding(it),
        uiState = uiState
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    uiState: MoreListUiState,
) = Column(
    modifier = modifier
) {
    MoreText(
        text = stringResource(id = StringResource.account),
        onClick = uiState.onAccount
    )
}

@Composable
private fun MoreText(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .clickable(
            onClickLabel = text,
            onClick = onClick
        )
) {
    Text(
        modifier = Modifier.padding(12.dp),
        text = text
    )
}

@Preview
@Composable
private fun Preview() = MoreListScreenCompose()