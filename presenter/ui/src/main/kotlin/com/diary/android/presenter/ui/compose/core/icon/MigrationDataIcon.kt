package com.diary.android.presenter.ui.compose.core.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SwitchAccount
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.diary.android.share.StringResource

@Composable
fun MigrationDataIcon(
    modifier: Modifier = Modifier,
    contentDescription: String = stringResource(id = StringResource.migration)
) = Icon(
    modifier = modifier,
    imageVector = Icons.Rounded.SwitchAccount,
    contentDescription = contentDescription
)

@Preview
@Composable
private fun Preview() = MigrationDataIcon()