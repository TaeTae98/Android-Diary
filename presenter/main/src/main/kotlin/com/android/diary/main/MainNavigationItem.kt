package com.android.diary.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Folder
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.ui.graphics.vector.ImageVector
import com.android.diary.domain.deeplink.DeepLink
import com.android.diary.share.StringResource

enum class MainNavigationItem(
    val icon: ImageVector,
    @StringRes
    val label: Int,
    val route: String,
) {
    Memo(
        icon = Icons.Rounded.Article,
        label = StringResource.memo,
        route = DeepLink.MEMO,
    ),
    Payment(
        icon = Icons.Rounded.AttachMoney,
        label = StringResource.payment,
        route = "payment",
    ),
    Tag(
        icon = Icons.Rounded.Tag,
        label = StringResource.tag,
        route = "tag",
    ),
    File(
        icon = Icons.Rounded.Folder,
        label = StringResource.file,
        route = "file",
    ),
    More(
        icon = Icons.Rounded.MoreHoriz,
        label = StringResource.more,
        route = DeepLink.MORE
    )
}
