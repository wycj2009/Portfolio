package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import kotlinx.datetime.LocalDate

@Composable
fun TimelineScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header()
        Spacer(modifier = Modifier.height(32.dp))
        Timeline(
            startDate = LocalDate(2010, 1, 1),
            endDate = LocalDate(2026, 2, 1),
        )

    }
}

@Composable
private fun Header() {
    Text(
        text = "Android Developer Portfolio",
        fontSize = 40.sp,
        style = Design.Text.baseStyle.copy(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF2563EB),
                    Color(0xFF9333EA),
                    Color(0xFFDB2777)
                )
            ),
        )
    )
}

@Composable
private fun Timeline(
    startDate: LocalDate,
    endDate: LocalDate,
    focusedIndex: Int? = null,
) {
    val markers = remember(startDate, endDate) { buildTimelineMarkers(startDate, endDate) }

    val markerSpacing = 12.dp
    val maxYearSize = 18.dp
    val maxMonthSize = 12.dp
    val totalHeight = remember(markers) {
        if (markers.isEmpty()) 0.dp else (markers.size - 1) * markerSpacing + maxYearSize
    }

    Box(
        modifier = Modifier
            .width(maxYearSize)
            .height(totalHeight)
    ) {
        markers.forEachIndexed { index, marker ->
            val isYearMarker = marker.month == 1 || index == 0
            val baseSize = if (isYearMarker) 14.dp else 8.dp
            val maxSize = if (isYearMarker) maxYearSize else maxMonthSize
            val isFocused = focusedIndex == index
            val dotSize = if (isFocused) maxSize else baseSize
            val color = if (isYearMarker) Color(0xFF2563EB) else Design.Color.gray400
            val yOffset = markerSpacing * index - (maxSize / 2)

            Box(
                modifier = Modifier
                    .offset(y = yOffset)
                    .width(maxSize)
                    .height(maxSize)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(dotSize)
                        .height(dotSize)
                        .background(color = color, shape = CircleShape)
                )
            }
        }
    }
}

private data class TimelineMarker(
    val year: Int,
    val month: Int,
)

private fun buildTimelineMarkers(
    startDate: LocalDate,
    endDate: LocalDate,
): List<TimelineMarker> {
    if (endDate < startDate) return emptyList()

    val startYear = startDate.year
    val startMonth = startDate.month.ordinal + 1
    val endYear = endDate.year
    val endMonth = endDate.month.ordinal + 1

    val markers = mutableListOf<TimelineMarker>().apply {
        var currentYear = startYear
        var currentMonth = startMonth

        while (currentYear < endYear || (currentYear == endYear && currentMonth <= endMonth)) {
            add(
                TimelineMarker(
                    year = currentYear,
                    month = currentMonth,
                )
            )

            if (currentMonth == 12) {
                currentYear++
                currentMonth = 1
            } else {
                currentMonth++
            }
        }
    }

    return markers
}
