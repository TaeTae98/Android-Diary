package com.diary.android.presenter.memo.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.android.diary.ui.compose.memo.MemoDetailScreenCompose

@Composable
fun MemoDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) = MemoDetailScreenCompose(
    modifier = modifier
)