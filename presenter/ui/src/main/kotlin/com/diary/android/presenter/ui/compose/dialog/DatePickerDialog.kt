package com.diary.android.presenter.ui.compose.dialog

import android.app.DatePickerDialog
import android.content.Context
import com.diary.android.share.StyleResource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun showDatePickerDialog(
    context: Context,
    date: LocalDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    onPick: (Int) -> Unit
) = DatePickerDialog(
    context, StyleResource.Theme_Diary_DatePickerdialog, { _, year, month, dayOfMonth ->
        onPick(LocalDate(year, month + 1, dayOfMonth).toEpochDays())
    }, date.year, date.month.ordinal, date.dayOfMonth
).show()

fun showDatePickerDialog(
    context: Context,
    epochDays: Int,
    onPick: (Int) -> Unit
) = showDatePickerDialog(context, LocalDate.fromEpochDays(epochDays), onPick)
