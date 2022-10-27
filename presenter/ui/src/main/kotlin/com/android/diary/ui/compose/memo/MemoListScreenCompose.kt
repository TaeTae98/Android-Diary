package com.android.diary.ui.compose.memo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.android.diary.ui.compose.core.button.AddFloatingButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoListScreenCompose(
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    floatingActionButton = { AddFloatingButton() }
) {
    Content(modifier = Modifier.padding(it))
}

@Composable
private fun Content(
    modifier: Modifier = Modifier
) = LazyColumn(
    modifier = modifier
) {

}