package com.diary.android.presenter.memo.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.android.diary.ui.compose.memo.MemoListScreenCompose

@Composable
fun MemoListScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) = MemoListScreenCompose(
    modifier = modifier
)