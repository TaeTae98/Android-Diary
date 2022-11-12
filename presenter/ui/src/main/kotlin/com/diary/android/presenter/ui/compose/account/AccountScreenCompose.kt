package com.diary.android.presenter.ui.compose.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource
import com.diary.android.presenter.ui.compose.core.button.NavigateUpButton
import com.diary.android.presenter.ui.compose.core.icon.LoginIcon
import com.diary.android.presenter.ui.compose.core.icon.LogoutIcon
import com.diary.android.presenter.ui.compose.core.icon.MigrationDataIcon
import com.diary.android.presenter.ui.compose.more.MoreText
import com.diary.android.presenter.ui.theme.DiaryDimen
import com.diary.android.presenter.ui.theme.DiaryTypography3
import com.diary.android.presenter.ui.uistate.account.AccountUiState

@Composable
fun AccountScreenCompose(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    uiState: AccountUiState = AccountUiState.SignOutState()
) = Scaffold(
    modifier = modifier,
    topBar = { TopBar(onNavigateUp = uiState.onNavigateUp) },
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
) {
    Content(
        modifier = Modifier.padding(it),
        uiState = uiState,
    )
}

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
    Profile(name = uiState.name, email = uiState.email)

    MoreText(
        icon = { MigrationDataIcon() },
        text = stringResource(id = StringResource.migration),
        onClick = uiState.onMigration
    )

    MoreText(
        icon = { LogoutIcon() },
        text = stringResource(id = StringResource.sign_out),
        onClick = uiState.onSignOut
    )
}

@Composable
private fun Profile(
    modifier: Modifier = Modifier,
    name: String?,
    email: String?
) = Card(
    modifier = modifier.fillMaxWidth()
) {
    Column(
        modifier = Modifier.padding(DiaryDimen.DEFAULT_CONTENT_PADDING)
    ) {
        name?.let {
            Text(
                text = it,
                style = DiaryTypography3.typography.titleLarge
            )
        }
        email?.let {
            Text(text = it)
        }
    }
}

@Composable
private fun SignOutState(
    modifier: Modifier = Modifier,
    uiState: AccountUiState.SignOutState
) = MoreText(
    modifier = modifier,
    icon = { LoginIcon() },
    text = stringResource(id = StringResource.sign_in),
    onClick = uiState.onSignIn
)

@Preview
@Composable
private fun Preview() = AccountScreenCompose()
