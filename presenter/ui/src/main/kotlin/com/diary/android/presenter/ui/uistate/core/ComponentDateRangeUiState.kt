package com.diary.android.presenter.ui.uistate.core

data class ComponentDateRangeUiState(
    val hasDate: Boolean = false,
    val setHasDate: (Boolean) -> Unit = {},
    val beginDate: Int = 0,
    val endDate: Int = 0,
    val setBeginDate: (Int) -> Unit = {},
    val setEndDate: (Int) -> Unit = {}
)
