package com.android.diary.ui.compose.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.diary.share.StringResource
import com.android.diary.ui.compose.core.button.NavigateUpButton
import com.android.diary.ui.uistate.account.AccountUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreenCompose(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    uiState: AccountUiState = AccountUiState.SignOutState()
) = Scaffold(
    modifier = modifier,
    topBar = { TopBar(onNavigateUp = uiState.onNavigateUp) },
    snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
) {
    Content(
        modifier = Modifier.padding(it),
        uiState = uiState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {}
) = TopAppBar(
    modifier = modifier,
    title = {
        Text(text = stringResource(id = StringResource.account))
    },
    navigationIcon = {
        NavigateUpButton(onClick = onNavigateUp)
    }
)

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    uiState: AccountUiState
) = when (uiState) {
    is AccountUiState.SignInState -> SignInState(
        modifier = modifier,
        uiState = uiState
    )
    is AccountUiState.SignOutState -> SignOutState(
        modifier = modifier,
        uiState = uiState
    )
}

@Composable
private fun SignInState(
    modifier: Modifier = Modifier,
    uiState: AccountUiState.SignInState
) = Column(modifier = modifier) {
    Text(text = uiState.displayName)
    Text(
        modifier = Modifier.clickable(
            onClickLabel = stringResource(id = StringResource.sign_out),
            onClick = uiState.onSignOut
        ),
        text = stringResource(id = StringResource.sign_out)
    )
}

@Composable
private fun SignOutState(
    modifier: Modifier = Modifier,
    uiState: AccountUiState.SignOutState
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .clickable(
            onClickLabel = stringResource(id = StringResource.sign_in),
            onClick = uiState.onSignIn
        )
) {
    Text(
        modifier = Modifier.padding(12.dp),
        text = stringResource(id = StringResource.sign_in)
    )
}

@Preview
@Composable
private fun Preview() = AccountScreenCompose()