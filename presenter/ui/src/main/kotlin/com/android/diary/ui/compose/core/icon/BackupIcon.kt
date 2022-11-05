package com.android.diary.ui.compose.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.diary.share.StringResource

@Composable
fun BackupIcon(
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = StringResource.backup)
) = Icon(
    modifier = modifier,
    imageVector = Icons.Rounded.Cloud,
    contentDescription = contentDescription
)