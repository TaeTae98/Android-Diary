package com.diary.android.presenter.ui.compose.more

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.presenter.ui.theme.DiaryDimen

@Composable
fun MoreText(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    text: String = "",
    onClick: () -> Unit = {}
) = Card(
    modifier = modifier.fillMaxWidth(),
    onClick = onClick,
) {
    Row(
        modifier = Modifier
            .padding(DiaryDimen.DEFAULT_CONTENT_PADDING),
        horizontalArrangement = Arrangement.spacedBy(DiaryDimen.DEFAULT_CONTENT_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        Text(text = text)
    }
}

@Preview
@Composable
private fun Preview() = MoreText()
