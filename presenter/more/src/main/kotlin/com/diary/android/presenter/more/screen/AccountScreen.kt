package com.diary.android.presenter.more.screen

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.diary.android.presenter.more.action.AccountAction
import com.diary.android.presenter.more.viewmodel.AccountViewModel
import com.diary.android.presenter.ui.compose.account.AccountScreenCompose
import com.diary.android.share.StringResource
import com.diary.android.share.oauth.GoogleOAuthRequest
import com.diary.android.share.oauth.GoogleOAuthResult
import com.diary.android.share.show
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    accountViewModel: AccountViewModel = hiltViewModel()
) = AccountScreenCompose(
    modifier = modifier,
    snackbarHostState = snackbarHostState,
    uiState = accountViewModel.uiState.collectAsState().value
).also {
    val oneTapSign = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            accountViewModel.saveGoogleOAuth(GoogleOAuthResult.OneTapSign(it.data))
        }
    }

    val sign = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            accountViewModel.saveGoogleOAuth(GoogleOAuthResult.Sign(it.data))
        }
    }

    val context = LocalContext.current
    LaunchedEffect(accountViewModel) {
        accountViewModel.action.collectLatest {
            when (it) {
                is AccountAction.NavigateUp -> navController.navigateUp()
                is AccountAction.GoogleSignIn -> googleLogin(
                    request = it.request,
                    onOneTapSign = oneTapSign::launch,
                    onSignIn = sign::launch
                )
                is AccountAction.Migration -> snackbarHostState.show(
                    message = context.getString(StringResource.finish)
                )
                is AccountAction.Failure -> snackbarHostState.show(
                    context = context,
                    throwable = it.throwable
                )
            }
        }
    }
}

private fun googleLogin(
    request: GoogleOAuthRequest,
    onOneTapSign: (IntentSenderRequest) -> Unit,
    onSignIn: (Intent) -> Unit
) = when (request) {
    is GoogleOAuthRequest.Sign -> onSignIn(request.intent)
    is GoogleOAuthRequest.OneTapSign -> onOneTapSign(request.intentSenderRequest)
}
