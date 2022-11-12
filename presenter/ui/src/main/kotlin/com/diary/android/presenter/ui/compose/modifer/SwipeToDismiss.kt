package com.diary.android.presenter.ui.compose.modifer

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.offset
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNot
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
fun Modifier.swipeToDismiss(
    orientation: Orientation = Orientation.Horizontal,
    enable: Boolean = true,
    onDismissed: (() -> Unit)? = null
): Modifier = composed {
    val state = rememberSwipeableState(initialValue = 0)
    val (size, setSize) = remember {
        mutableStateOf(0)
    }
    val anchors = if (size == 0) {
        mapOf(0F to 0)
    } else {
        mapOf(
            -size.toFloat() to -1,
            0F to 0,
            size.toFloat() to 1
        )
    }

    LaunchedEffect(state, onDismissed) {
        snapshotFlow {
            state.currentValue to state.isAnimationRunning
        }.distinctUntilChanged().filterNot {
            it.second
        }.collect {
            if (it.first != 0) {
                onDismissed?.invoke()
            }
        }
    }

    onGloballyPositioned {
        val measuredSize = if (orientation == Orientation.Horizontal) {
            it.size.width
        } else {
            it.size.height
        }

        setSize(measuredSize)
    }
        .offset {
            IntOffset(
                x = if (orientation == Orientation.Horizontal) state.offset.value.roundToInt() else 0,
                y = if (orientation == Orientation.Vertical) state.offset.value.roundToInt() else 0,
            )
        }
        .swipeable(
            state = state,
            anchors = anchors,
            orientation = orientation,
            enabled = enable
        )
}
