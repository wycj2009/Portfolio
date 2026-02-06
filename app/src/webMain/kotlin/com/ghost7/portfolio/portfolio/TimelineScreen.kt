package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
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
            projects = listOf(
                Project(
                    startDate = LocalDate(
                        year = 2010,
                        month = 1,
                        day = 1,
                    ),
                    endDate = LocalDate(
                        year = 2012,
                        month = 1,
                        day = 1,
                    ),
                ),
                Project(
                    startDate = LocalDate(
                        year = 2026,
                        month = 1,
                        day = 1,
                    ),
                    endDate = LocalDate(
                        year = 2026,
                        month = 9,
                        day = 1,
                    ),
                ),
            ),
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
    projects: List<Project>,
    focusedIndex: Int? = null,
) {
    val markers = Marker.fromProjects(projects = projects)

    val markerSpacing = 20.dp
    val maxYearSize = 18.dp
    val maxMonthSize = 12.dp
    val timelineWidth = 260.dp
    val yearTextSize = 16.sp
    val monthTextSize = 12.sp
    val totalHeight = remember(markers) {
        if (markers.isEmpty()) 0.dp else (markers.size - 1) * markerSpacing + maxYearSize
    }

    Box(
        modifier = Modifier
            .width(timelineWidth)
            .height(totalHeight)
    ) {
        markers.forEachIndexed { index, marker ->
            val isYearMarker = marker.month == 1 || index == 0
            val baseSize = if (isYearMarker) 14.dp else 8.dp
            val maxSize = if (isYearMarker) maxYearSize else maxMonthSize
            val isFocused = focusedIndex == index
            val dotSize = if (isFocused) maxSize else baseSize
            val color = if (isYearMarker) Color(0xFF2563EB) else Design.Color.gray400
            val yOffset = markerSpacing * index

            Box(
                modifier = Modifier
                    .offset(y = yOffset - (maxSize / 2))
                    .width(timelineWidth)
                    .height(maxSize)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                val label = if (isYearMarker) "${marker.year}년" else "${marker.month}월"
                val labelSize = if (isYearMarker) yearTextSize else monthTextSize
                val labelWeight = if (isYearMarker) FontWeight.SemiBold else FontWeight.Normal
                val labelColor = if (isYearMarker) Design.Color.gray800 else Design.Color.gray600

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxSize),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(maxSize),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = label,
                            fontSize = labelSize,
                            fontWeight = labelWeight,
                            color = labelColor,
                            lineHeight = labelSize
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(dotSize)
                            .height(dotSize)
                            .background(color = color, shape = CircleShape)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(maxSize),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = label,
                            fontSize = labelSize,
                            fontWeight = labelWeight,
                            color = labelColor,
                            lineHeight = labelSize
                        )
                    }
                }
            }
        }
    }
}
