package com.diary.android.presenter.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.datetime.LocalDate

@Composable
fun rememberLocalDateSaveable(
    init: () -> LocalDate
) = rememberSaveable(
    saver = Saver(
        save = { it.toEpochDays() },
        restore = { LocalDate.fromEpochDays(it) }
    ),
    init = init
)
