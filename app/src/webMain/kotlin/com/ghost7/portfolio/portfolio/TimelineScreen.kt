package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
    val markers = remember(startDate, endDate) {
        buildTimelineMarkers(startDate, endDate)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        markers.forEachIndexed { index, marker ->
            val baseSize = if (marker.isYear) 14.dp else 8.dp
            val maxSize = if (marker.isYear) 18.dp else 12.dp
            val isFocused = focusedIndex == index
            val dotSize = if (isFocused) maxSize else baseSize
            val color = if (marker.isYear) Color(0xFF2563EB) else Design.Color.gray400
            Box(
                modifier = Modifier
                    .width(maxSize)
                    .height(maxSize),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(dotSize)
                        .height(dotSize)
                        .background(color = color, shape = CircleShape)
                )
                if (index != markers.lastIndex) {
                    Spacer(modifier = Modifier.height(12.dp))
                }
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
