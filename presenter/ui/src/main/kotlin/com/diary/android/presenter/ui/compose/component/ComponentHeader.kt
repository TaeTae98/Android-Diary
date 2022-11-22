package com.diary.android.presenter.ui.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.presenter.ui.compose.core.input.DescriptionTextField
import com.diary.android.presenter.ui.compose.core.input.TitleTextField
import com.diary.android.presenter.ui.uistate.core.TextInputUiState

@Composable
fun ComponentHeader(
    modifier: Modifier = Modifier,
    titleUiState: TextInputUiState = TextInputUiState(),
    descriptionUiState: TextInputUiState = TextInputUiState()
) = Card(
    modifier = modifier
) {
    Column {
        TitleTextField(uiState = titleUiState)
        DescriptionTextField(uiState = descriptionUiState)
    }
}

@Preview
@Composable
private fun Preview() = ComponentHeader()
