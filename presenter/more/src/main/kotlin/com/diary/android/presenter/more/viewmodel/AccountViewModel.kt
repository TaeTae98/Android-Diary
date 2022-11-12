package com.diary.android.presenter.more.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.android.domain.model.DiaryAccount
import com.diary.android.domain.model.IdToken
import com.diary.android.domain.usecase.account.GetDiaryAccountUseCase
import com.diary.android.domain.usecase.account.SignInUseCase
import com.diary.android.domain.usecase.account.SignOutUseCase
import com.diary.android.domain.usecase.data.MigrationDataUseCase
import com.diary.android.presenter.more.action.AccountAction
import com.diary.android.presenter.ui.uistate.account.AccountUiState
import com.diary.android.share.oauth.GoogleOAuth
import com.diary.android.share.oauth.GoogleOAuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val googleOAuth: GoogleOAuth,
    private val signInUseCase: SignInUseCase,
    private val migrationDataUseCase: MigrationDataUseCase,
    private val signOutUseCase: SignOutUseCase,
    getDiaryAccountUseCase: GetDiaryAccountUseCase,
) : ViewModel() {
    private val _action = MutableSharedFlow<AccountAction>()
    val action = _action.asSharedFlow()

    private val account = getDiaryAccountUseCase(Unit).onFailure {
        viewModelScope.launch { _action.emit(AccountAction.Failure(it)) }
    }.getOrDefault(flowOf(DiaryAccount.Guest))

    val uiState = account.map {
        when (it) {
            is DiaryAccount.User -> AccountUiState.SignInState(
                onNavigateUp = ::navigateUp,
                name = it.name,
                email = it.email,
                onMigration = ::migration,
                onSignOut = ::signOut
            )
            is DiaryAccount.Guest -> AccountUiState.SignOutState(
                onNavigateUp = ::navigateUp,
                onSignIn = ::signIn
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = AccountUiState.SignOutState(
            onNavigateUp = ::navigateUp,
            onSignIn = ::signIn
        )
    )

    private fun navigateUp() = viewModelScope.launch {
        _action.emit(AccountAction.NavigateUp)
    }

    private fun signIn() = viewModelScope.launch {
        googleOAuth.signIn().onSuccess {
            _action.emit(AccountAction.GoogleSignIn(it))
        }.onFailure {
            _action.emit(AccountAction.Failure(it))
        }
    }

    private suspend fun signIn(idToken: String?) = signInUseCase(
        IdToken(token = idToken)
    ).onFailure {
        _action.emit(AccountAction.Failure(it))
    }

    private fun migration() = viewModelScope.launch {
        migrationDataUseCase(Unit).onSuccess {
            _action.emit(AccountAction.Migration)
        }.onFailure {
            _action.emit(AccountAction.Failure(it))
        }
    }

    private fun signOut() = viewModelScope.launch {
        signOutUseCase(Unit).onFailure {
            _action.emit(AccountAction.Failure(it))
        }
    }

    fun saveGoogleOAuth(request: GoogleOAuthResult) = viewModelScope.launch {
        googleOAuth.getIdToken(request).onSuccess {
            signIn(it)
        }.onFailure {
            _action.emit(AccountAction.Failure(it))
        }
    }
}
