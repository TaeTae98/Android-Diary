package com.android.diary.ui.compose.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.android.diary.share.StringResource

@Composable
fun LogoutIcon(
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = StringResource.sign_out)
) = Icon(
    modifier = modifier,
    imageVector = Icons.Rounded.Logout,
    contentDescription = contentDescription
)

@Preview
@Composable
private fun Preview() = LogoutIcon()