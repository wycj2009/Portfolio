package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
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
    val projectMarkerIndexRanges = projects.map { it.getMarkerIndexRange(markers) }
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val baseTextStyle = Design.Text.baseStyle

    val markerSpacing = 30.dp
    val yearDotRadius = 10.dp
    val monthDotRadius = 6.dp
    val yearDotColor = Color(0xFF2563EB)
    val monthDotColor = Design.Color.gray500
    val textSpacing = 20.dp
    val yearTextSize = 16.sp
    val monthTextSize = 12.sp
    val yearTextColor = Color(0xFF2563EB)
    val monthTextColor = Design.Color.gray500
    val projectIconSpacing = 150.dp
    val projectIconSize = 60.dp
    val projectIconLineStrokeWidth = 2.dp
    val projectIconLineColor = Design.Color.gray300
    val totalHeight = markerSpacing * (markers.size - 1)
    val focusedScale = 1.25f

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(totalHeight).background(Design.Color.gray200),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            projectMarkerIndexRanges.forEachIndexed { index, projectMarkerIndexRange ->
                val markerX = size.width * 0.5f
                val markerY = with(density) { (markerSpacing * projectMarkerIndexRange.first).toPx() }

                drawLine(
                    color = projectIconLineColor,
                    start = Offset(
                        x = if (index % 2 == 0) {
                            markerX - with(density) { (projectIconSpacing + (projectIconSize * 0.5f)).toPx() }
                        } else {
                            markerX + with(density) { (projectIconSpacing + (projectIconSize * 0.5f)).toPx() }
                        },
                        y = markerY,
                    ),
                    end = Offset(x = markerX, y = markerY),
                    strokeWidth = with(density) { projectIconLineStrokeWidth.toPx() }
                )
            }
            markers.forEachIndexed { index, marker ->
                val isYearMarker = marker.month == 1
                val x = size.width * 0.5f
                val y = with(density) { (markerSpacing * index).toPx() }
                val scale = if (false) focusedScale else 1f

                val dotRadius = with(density) { (if (isYearMarker) yearDotRadius else monthDotRadius).toPx() } * scale
                val dotColor = if (isYearMarker) yearDotColor else monthDotColor
                drawCircle(
                    center = Offset(x = x, y = y),
                    radius = dotRadius,
                    color = dotColor,
                )

                val textLayout = textMeasurer.measure(
                    text = if (isYearMarker) "${marker.year}년" else "${marker.month}월",
                    style = baseTextStyle.copy(
                        fontSize = (if (isYearMarker) yearTextSize else monthTextSize) * scale,
                        color = if (isYearMarker) yearTextColor else monthTextColor,
                    ),
                )
                val textX = x + with(density) { textSpacing.toPx() }
                val textY = y - (textLayout.size.height * 0.5f)
                drawText(
                    textLayoutResult = textLayout,
                    topLeft = Offset(x = textX, y = textY),
                )
            }
        }
        projects.forEachIndexed { index, project ->
            val markerIndexRange = projectMarkerIndexRanges[index]
            val markerX = maxWidth * 0.5f
            val markerY = markerSpacing * markerIndexRange.first
            val logoX = if (index % 2 == 0) {
                markerX - projectIconSpacing - projectIconSize
            } else {
                markerX + projectIconSpacing
            }
            val logoY = markerY - (projectIconSize * 0.5f)

            Box(
                modifier = Modifier
                    .offset(x = logoX, y = logoY)
                    .size(projectIconSize)
                    .background(Design.Color.black)
            )
        }
    }
}
