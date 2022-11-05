package com.android.diary.ui.uistate.account

sealed interface AccountUiState {
    val onNavigateUp: () -> Unit

    data class SignInState(
        override val onNavigateUp: () -> Unit = {},
        val displayName: String = "",
        val onSignOut: () -> Unit = {}
    ) : AccountUiState

    data class SignOutState(
        override val onNavigateUp: () -> Unit = {},
        val onSignIn: () -> Unit = {}
    ) : AccountUiState
}