package com.android.diary.ui.compose.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CloudUpload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.android.diary.share.StringResource

@Composable
fun UploadIcon(
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = StringResource.upload)
) = Icon(
    modifier = modifier,
    imageVector = Icons.Rounded.CloudUpload,
    contentDescription = contentDescription
)