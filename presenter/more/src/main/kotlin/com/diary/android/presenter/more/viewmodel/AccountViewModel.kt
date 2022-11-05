package com.diary.android.presenter.more.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.diary.domain.model.AccountType
import com.android.diary.domain.model.DiaryAccount
import com.android.diary.domain.usecase.account.GetDiaryAccountUseCase
import com.android.diary.domain.usecase.account.SignInUseCase
import com.android.diary.domain.usecase.account.SignOutUseCase
import com.android.diary.share.oauth.GoogleOAuth
import com.android.diary.share.oauth.GoogleOAuthResult
import com.android.diary.ui.uistate.account.AccountUiState
import com.diary.android.presenter.more.action.AccountAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val googleOAuth: GoogleOAuth,
    private val signInUseCase: SignInUseCase,
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
        SignInUseCase.Params(
            idToken = idToken,
            type = AccountType.GOOGLE
        )
    ).onFailure {
        _action.emit(AccountAction.Failure(it))
    }

    private fun signOut() = viewModelScope.launch {
        signOutUseCase(Unit)
    }

    fun saveGoogleOAuth(request: GoogleOAuthResult) = viewModelScope.launch {
        googleOAuth.getIdToken(request).onSuccess {
            signIn(it)
        }.onFailure {
            _action.emit(AccountAction.Failure(it))
        }
    }
}