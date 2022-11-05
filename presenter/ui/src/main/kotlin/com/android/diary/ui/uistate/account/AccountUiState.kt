package com.android.diary.ui.uistate.account

sealed interface AccountUiState {
    val onNavigateUp: () -> Unit

    data class SignInState(
        override val onNavigateUp: () -> Unit = {},
        val name: String? = null,
        val email: String? = null,
        val onMigration: () -> Unit = {},
        val onSignOut: () -> Unit = {}
    ) : AccountUiState

    data class SignOutState(
        override val onNavigateUp: () -> Unit = {},
        val onSignIn: () -> Unit = {}
    ) : AccountUiState
}