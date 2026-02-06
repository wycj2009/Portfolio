package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
        Spacer(Modifier.height(32.dp))
        Header()
        Spacer(Modifier.height(64.dp))
        Timeline(
            projects = listOf(
                Project(
                    startDate = LocalDate(year = 2010, month = 1, day = 1),
                    endDate = LocalDate(year = 2012, month = 1, day = 1),
                ),
                Project(
                    startDate = LocalDate(year = 2026, month = 1, day = 1),
                    endDate = LocalDate(year = 2026, month = 9, day = 1),
                ),
            ),
        )
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun ColumnScope.Header() {
    Text(
        text = "Android Developer Portfolio",
        style = Design.Text.baseStyle.copy(
            fontSize = 40.sp,
            brush = Brush.linearGradient(
                colors = listOf(
                    Color(0xFF2563EB),
                    Color(0xFF9333EA),
                    Color(0xFFDB2777),
                ),
            ),
        )
    )
}

@Composable
private fun ColumnScope.Timeline(
    projects: List<Project>,
) {
    val markers = Marker.fromProjects(projects = projects)
    val density = LocalDensity.current

    val markerSpacing = 28.dp
    val yearDotSize = 10.dp
    val monthDotSize = 6.dp
    val yearDotColor = Color(0xFF2563EB)
    val monthDotColor = Design.Color.gray400
    val yearTextSize = 16.sp
    val monthTextSize = 12.sp
    val focusedScaleFraction = 1.5f
    val totalHeight = markerSpacing * (markers.size - 1)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(totalHeight).background(Design.Color.gray200),
    ) {
        markers.forEachIndexed { index, marker ->
            val xOffset = size.width * 0.5f
            val yOffset = with(density) { (markerSpacing * index).toPx() }
            val isYearMarker = marker.month == 1
            val dotSize = with(density) { (if (isYearMarker) yearDotSize else monthDotSize).toPx() }
            val dotColor = if (isYearMarker) yearDotColor else monthDotColor

            drawCircle(
                center = Offset(x = xOffset, y = yOffset),
                radius = dotSize,
                color = dotColor,
            )
        }
    }
}
