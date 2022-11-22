package com.diary.android.presenter.ui.compose.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.diary.android.domain.utils.onTrue
import com.diary.android.presenter.ui.compose.dialog.showDatePickerDialog
import com.diary.android.presenter.ui.theme.DiaryColor3
import com.diary.android.presenter.ui.theme.DiaryDimen
import com.diary.android.presenter.ui.theme.DiaryTypography3
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import java.time.format.TextStyle
import java.util.Locale
import kotlin.math.abs

private const val PAGE_COUNT = Int.MAX_VALUE
private const val INITIAL_PAGE = PAGE_COUNT / 2
private const val DAY_OF_WEEK_COUNT = 7
private const val WEEK_OF_MONTH_COUNT = 6
private const val EDGE_PERCENT = 0.075F
private const val DRAG_EDGE_DELAY = 750L
private val DEFAULT_DATE = Clock.System.todayIn(TimeZone.currentSystemDefault())

private fun Int.toCalendarIndex() = minus(INITIAL_PAGE)
private fun Int.toCalendarPage() = plus(INITIAL_PAGE)
private fun PagerState.getLocalDate(row: Int, column: Int): LocalDate {
    val localDate = DEFAULT_DATE.plus(currentPage.toCalendarIndex(), DateTimeUnit.MONTH)
    return localDate.plus(row * 7 + column - (localDate.dayOfMonth + localDate.dayOfWeek.value % 7), DateTimeUnit.DAY)
}

@Immutable
private data class Range(
    val from: LocalDate,
    val to: LocalDate
)

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    onPickDateRange: (beginDate: Int, endDate: Int) -> Unit = { _, _ -> }
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = INITIAL_PAGE)
    val (range, setRange) = remember { mutableStateOf<Range?>(null) }
    var monthHeaderSize by remember { mutableStateOf(IntSize.Zero) }

    HorizontalPager(
        modifier = modifier.calendarTouchEvent(
            pagerState = pagerState,
            topPadding = monthHeaderSize.height,
            onDrag = { beginDate, endDate -> setRange(Range(beginDate, endDate)) },
            onDragCanceled = { setRange(null) },
            onDragFinished = { beginDate, endDate ->
                setRange(null)
                onPickDateRange(beginDate.toEpochDays(), endDate.toEpochDays())
            }
        ),
        count = PAGE_COUNT,
        state = pagerState,
        key = { it }
    ) { page ->
        val month = remember(page) { DEFAULT_DATE.plus(page.toCalendarIndex(), DateTimeUnit.MONTH) }

        Month(
            year = month.year,
            month = month.month,
            range = range?.takeIf { page == pagerState.currentPage },
            onMonthHeaderLongClick = remember {
                {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(page = INITIAL_PAGE)
                    }
                }
            },
            onPickDate = remember {
                {
                    val date = LocalDate.fromEpochDays(it)
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(((date.year - DEFAULT_DATE.year) * 12 + date.month.value - DEFAULT_DATE.month.value).toCalendarPage())
                    }
                }
            },
            setMonthHeaderSize = { monthHeaderSize = it }
        )
    }
}

@Composable
private fun Month(
    modifier: Modifier = Modifier,
    year: Int,
    month: Month,
    range: Range? = null,
    onMonthHeaderLongClick: () -> Unit,
    onPickDate: (Int) -> Unit,
    setMonthHeaderSize: (IntSize) -> Unit,
) = Card(
    modifier = modifier
) {
    val monthDate = remember(year, month) { LocalDate(year, month, 1) }

    Column {
        MonthHeader(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            year = year,
            month = month,
            onLongClick = onMonthHeaderLongClick,
            onPickDate = onPickDate,
            setMonthHeaderSize = setMonthHeaderSize,
        )

        repeat(WEEK_OF_MONTH_COUNT) {
            val begin = remember(monthDate, it) { monthDate.plus(it * DAY_OF_WEEK_COUNT - monthDate.dayOfWeek.value % 7, DateTimeUnit.DAY) }
            val end = remember(begin) { begin.plus(DAY_OF_WEEK_COUNT - 1, DateTimeUnit.DAY) }
            val copyRange = remember(range, begin, end) {
                range?.takeIf { range ->
                    range.from in begin..end || range.to in begin..end || (range.from < begin && end < range.to)
                }?.copy(
                    from = if (range.from < begin) {
                        begin.minus(1, DateTimeUnit.DAY)
                    } else {
                        maxOf(range.from, begin)
                    },
                    to = if (range.to > end) {
                        end.plus(1, DateTimeUnit.DAY)
                    } else {
                        minOf(range.to, end)
                    }
                )
            }

            Week(
                modifier = Modifier.weight(1F),
                year = year,
                month = month,
                weekOfMonth = it,
                range = copyRange
            )
        }
    }
}

@Composable
private fun MonthHeader(
    modifier: Modifier = Modifier,
    year: Int,
    month: Month,
    onLongClick: () -> Unit,
    onPickDate: (Int) -> Unit,
    setMonthHeaderSize: (IntSize) -> Unit,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .onSizeChanged(setMonthHeaderSize)
            .combinedClickable(
                onLongClickLabel = "",
                onLongClick = onLongClick,
                onClick = {
                    showDatePickerDialog(context, LocalDate(year, month, 1), onPickDate)
                }
            )
    ) {
        Column(
            modifier = Modifier
                .padding(DiaryDimen.DEFAULT_CONTENT_PADDING)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = year.toString(),
                style = DiaryTypography3.typography.headlineSmall
            )
            Text(
                text = month.getDisplayName(TextStyle.FULL, Locale.getDefault()),
                style = DiaryTypography3.typography.headlineLarge
            )
        }
    }
}

@Composable
private fun Week(
    modifier: Modifier = Modifier,
    year: Int,
    month: Month,
    weekOfMonth: Int,
    range: Range? = null,
) = Box(
    modifier = modifier
) {
    val week = remember(year, month, weekOfMonth) { LocalDate(year, month, 1).plus(weekOfMonth, DateTimeUnit.WEEK) }

    Row {
        repeat(DAY_OF_WEEK_COUNT) {
            val date = remember(week, it) { week.plus(it - (week.dayOfWeek.value % 7), DateTimeUnit.DAY) }
            WeekHeader(
                modifier = Modifier.weight(1F),
                isSameMonth = month == date.month,
                dayOfMonth = date.dayOfMonth,
                dayOfWeek = date.dayOfWeek
            )
        }
    }

    range?.let { range ->
        Row {
            repeat(DAY_OF_WEEK_COUNT) {
                val date = remember(week, it) { week.plus(it - (week.dayOfWeek.value % 7), DateTimeUnit.DAY) }
                when {
                    date == range.from && date == range.to -> RangeBody()
                    date == range.from -> RangeHeader()
                    date == range.to -> RangeTail()
                    date in range.from..range.to -> RangeBody()
                    else -> Empty()
                }
            }
        }
    }
}

@Composable
private fun RowScope.RangeBody(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
        .weight(1F)
        .fillMaxHeight()
        .background(DiaryColor3.colorScheme.primary.copy(alpha = 0.5F))
)

@Composable
private fun RowScope.RangeHeader(
    modifier: Modifier = Modifier

) = Box(
    modifier = modifier
        .weight(1F)
        .fillMaxHeight()
        .background(
            color = DiaryColor3.colorScheme.primary.copy(alpha = 0.5F),
            shape = RoundedCornerShape(topStartPercent = 50)
        )
)

@Composable
private fun RowScope.RangeTail(
    modifier: Modifier = Modifier

) = Box(
    modifier = modifier
        .weight(1F)
        .fillMaxHeight()
        .background(
            color = DiaryColor3.colorScheme.primary.copy(alpha = 0.5F),
            shape = RoundedCornerShape(bottomEndPercent = 50)
        )
)

@Composable
private fun RowScope.Empty(
    modifier: Modifier = Modifier
) = Box(
    modifier = modifier
        .weight(1F)
        .background(Color.Transparent)
)

@Composable
private fun WeekHeader(
    modifier: Modifier = Modifier,
    isSameMonth: Boolean,
    dayOfMonth: Int,
    dayOfWeek: DayOfWeek,
) = Text(
    modifier = modifier,
    text = dayOfMonth.toString(),
    color = when (dayOfWeek) {
        DayOfWeek.SUNDAY -> Color.Red
        DayOfWeek.SATURDAY -> Color.Blue
        else -> Color.Black
    }.copy(
        alpha = if (isSameMonth) {
            1F
        } else {
            0.5F
        }
    ),
    textAlign = TextAlign.Center
)

private fun Modifier.calendarTouchEvent(
    pagerState: PagerState,
    isEnable: Boolean = true,
    topPadding: Int = 0,
    onDrag: (beginDate: LocalDate, endDate: LocalDate) -> Unit = { _, _ -> },
    onDragCanceled: () -> Unit = {},
    onDragFinished: (beginDate: LocalDate, endDate: LocalDate) -> Unit = { _, _ -> },
) = composed {
    val coroutineScope = rememberCoroutineScope()
    val haptic = LocalHapticFeedback.current
    val (time, setTime) = remember { mutableStateOf(0L) }

    pointerInput(isEnable, topPadding, time) {
        val itemWidth = size.width / DAY_OF_WEEK_COUNT
        val itemHeight = (size.height - topPadding) / WEEK_OF_MONTH_COUNT
        isEnable.onTrue {
            var baseDate: LocalDate? = null
            var dragDate: LocalDate? = null
            var onDragEdgeConsumedTime = 0L
            detectDragGesturesAfterLongPress(
                onDragStart = {
                    if (it.y <= topPadding) {
                        setTime(time + 1)
                    } else {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        baseDate = pagerState.getLocalDate(((it.y - topPadding) / itemHeight).toInt(), (it.x / itemWidth).toInt())
                    }
                },
                onDragEnd = { onDragFinished(minOf(baseDate ?: DEFAULT_DATE, dragDate ?: DEFAULT_DATE), maxOf(baseDate ?: DEFAULT_DATE, dragDate ?: DEFAULT_DATE)) },
                onDragCancel = onDragCanceled
            ) { change, _ ->
                dragDate = pagerState.getLocalDate(((change.position.y - topPadding) / itemHeight).toInt(), (change.position.x / itemWidth).toInt())
                onDrag(minOf(baseDate ?: DEFAULT_DATE, dragDate ?: DEFAULT_DATE), maxOf(baseDate ?: DEFAULT_DATE, dragDate ?: DEFAULT_DATE))
                when {
                    change.position.x <= size.width * EDGE_PERCENT && abs(onDragEdgeConsumedTime - change.uptimeMillis) >= DRAG_EDGE_DELAY -> {
                        onDragEdgeConsumedTime = change.uptimeMillis
                        coroutineScope.launch {
                            pagerState.animateScrollToPage((pagerState.currentPage - 1).coerceAtLeast(0))
                        }
                    }
                    change.position.x >= size.width * (1 - EDGE_PERCENT) && abs(onDragEdgeConsumedTime - change.uptimeMillis) >= DRAG_EDGE_DELAY -> {
                        onDragEdgeConsumedTime = change.uptimeMillis
                        coroutineScope.launch {
                            pagerState.animateScrollToPage((pagerState.currentPage + 1).coerceAtMost(PAGE_COUNT))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() = Calendar()
