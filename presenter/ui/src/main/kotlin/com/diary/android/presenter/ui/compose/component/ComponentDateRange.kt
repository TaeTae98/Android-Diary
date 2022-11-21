package com.diary.android.presenter.ui.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.domain.utils.onTrue
import com.diary.android.presenter.ui.compose.dialog.showDatePickerDialog
import com.diary.android.presenter.ui.theme.DiaryDimen
import com.diary.android.presenter.ui.uistate.core.ComponentDateRangeUiState
import com.diary.android.share.StringResource
import kotlinx.datetime.LocalDate

@Composable
fun ComponentDateRange(
    modifier: Modifier = Modifier,
    uiState: ComponentDateRangeUiState = ComponentDateRangeUiState()
) = Card(modifier = modifier) {
    Column {
        Header(uiState = uiState)
        uiState.hasDate.onTrue {
            DateRange(uiState = uiState)
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
    uiState: ComponentDateRangeUiState
) = Row(modifier = modifier) {
    Text(
        modifier = Modifier.weight(1F).padding(DiaryDimen.DEFAULT_CONTENT_PADDING),
        text = stringResource(id = StringResource.date)
    )
    Switch(
        checked = uiState.hasDate,
        onCheckedChange = uiState.setHasDate
    )
}

@Composable
private fun DateRange(
    modifier: Modifier = Modifier,
    uiState: ComponentDateRangeUiState,
) = Row(
    modifier = modifier,
) {
    DateText(
        epochDays = uiState.beginDate,
        onClickLabel = stringResource(id = StringResource.begin_date),
        onPickDate = uiState.setBeginDate
    )
    Text(
        modifier = Modifier.padding(DiaryDimen.DEFAULT_CONTENT_PADDING),
        text = "~"
    )
    DateText(
        epochDays = uiState.endDate,
        onClickLabel = stringResource(id = StringResource.end_date),
        onPickDate = uiState.setEndDate
    )
}

@Composable
private fun RowScope.DateText(
    modifier: Modifier = Modifier,
    epochDays: Int,
    onClickLabel: String,
    onPickDate: (Int) -> Unit,
) {
    val context = LocalContext.current
    val date = remember(epochDays) { LocalDate.fromEpochDays(epochDays) }

    Box(
        modifier = modifier
            .weight(1F)
            .clickable(
                onClickLabel = onClickLabel,
                onClick = { showDatePickerDialog(context, date, onPickDate) }
            ),
    ) {
        Text(
            modifier = Modifier
                .padding(DiaryDimen.DEFAULT_CONTENT_PADDING)
                .fillMaxWidth(),
            text = date.toString(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun Preview() = ComponentDateRange()

@Preview
@Composable
private fun PreviewHasDate() = ComponentDateRange(uiState = ComponentDateRangeUiState(hasDate = true))
