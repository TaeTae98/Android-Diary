package com.diary.android.presenter.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Article
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.MoreHoriz
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.ui.graphics.vector.ImageVector
import com.diary.android.domain.deeplink.DeepLink
import com.diary.android.share.StringResource

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
    Calendar(
        icon = Icons.Rounded.DateRange,
        label = StringResource.calendar,
        route = DeepLink.CALENDAR
    ),
    Tag(
        icon = Icons.Rounded.Tag,
        label = StringResource.tag,
        route = "tag",
    ),
    Payment(
        icon = Icons.Rounded.AttachMoney,
        label = StringResource.payment,
        route = "payment",
    ),
    More(
        icon = Icons.Rounded.MoreHoriz,
        label = StringResource.more,
        route = DeepLink.MORE
    )
}
