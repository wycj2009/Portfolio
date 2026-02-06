package com.ghost7.portfolio.portfolio

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimelineScreen() {
    val projects = listOf(
        Project(
            startDate = LocalDate(year = 2010, month = 1, day = 1),
            endDate = LocalDate(year = 2012, month = 1, day = 1),
        ),
        Project(
            startDate = LocalDate(year = 2026, month = 2, day = 1),
            endDate = LocalDate(year = 2026, month = 9, day = 1),
        ),
    )
    val markers = Marker.fromProjects(projects = projects)
    val projectMarkerIndexRanges = projects.map { it.getMarkerIndexRange(markers) }
    val density = LocalDensity.current

    val markerSpacing = 30.dp
    val yearDotRadius = 10.dp
    val monthDotRadius = 6.dp
    val yearDotColor = Color(0xFF2563EB)
    val monthDotColor = Design.Color.gray500
    val textSpacing = 20.dp
    val yearTextSize = 16.sp
    val monthTextSize = 12.sp
    val yearTextColor = Color(0xFF2563EB)
    val monthTextColor = Design.Color.gray600
    val projectIconSpacing = 200.dp
    val projectIconSize = 60.dp
    val projectIconLineColor = Design.Color.gray300
    val totalHeight = markerSpacing * (markers.size - 1)
    var hoveredProject: Project? by remember { mutableStateOf(null) }
    val focusedMarkerIndexRange = hoveredProject?.getMarkerIndexRange(markers) ?: IntRange.EMPTY
    val focusedScale by animateFloatAsState(if (hoveredProject == null) 1f else 1.4f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = {
                    hoveredProject = null
                },
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(32.dp))
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
        Spacer(Modifier.height(64.dp))
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(totalHeight),
        ) {
            // projectIconLine
            projectMarkerIndexRanges.forEachIndexed { index, projectMarkerIndexRange ->
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * projectMarkerIndexRange.first
                val lineWidth = projectIconSpacing + (projectIconSize * 0.5f)
                val lineStrokeWidth = markers.getOrNull(projectMarkerIndexRange.first)?.let { marker ->
                    if (marker.month == 1) yearDotRadius * 2 else monthDotRadius * 2
                } ?: 0.dp
                val scale = if (hoveredProject == projects[index]) focusedScale else 1f

                Box(
                    modifier = Modifier
                        .offset(
                            x = if (index % 2 == 0) markerX - lineWidth else markerX,
                            y = markerY - (lineStrokeWidth * 0.5f),
                        )
                        .size(width = lineWidth, height = lineStrokeWidth)
                        .graphicsLayer {
                            scaleY = scale
                            transformOrigin = if (index % 2 == 0) {
                                TransformOrigin(1f, 0.5f)
                            } else {
                                TransformOrigin(0f, 0.5f)
                            }
                        }
                        .background(projectIconLineColor)
                )
            }
            // marker, text
            markers.forEachIndexed { index, marker ->
                val isYearMarker = marker.month == 1
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * index
                val scale = if (index in focusedMarkerIndexRange) focusedScale else 1f

                val dotRadius = if (isYearMarker) yearDotRadius else monthDotRadius
                val dotColor = if (isYearMarker) yearDotColor else monthDotColor
                Box(
                    modifier = Modifier
                        .offset(
                            x = markerX - dotRadius,
                            y = markerY - dotRadius,
                        )
                        .size(dotRadius * 2f)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .background(color = dotColor, shape = CircleShape)
                )

                val textSize = if (isYearMarker) yearTextSize else monthTextSize
                val textColor = if (isYearMarker) yearTextColor else monthTextColor
                Text(
                    text = if (isYearMarker) "${marker.year}년" else "${marker.month}월",
                    style = Design.Text.baseStyle.copy(
                        fontSize = textSize,
                        color = textColor,
                    ),
                    modifier = Modifier
                        .offset(
                            x = markerX + textSpacing,
                            y = markerY - (with(density) { textSize.toDp() } * 0.77f),
                        )
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin(0f, 0.5f)
                        }
                )
            }
            // projectIcon
            projects.forEachIndexed { index, project ->
                val markerIndexRange = projectMarkerIndexRanges[index]
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * markerIndexRange.first
                val scale = if (hoveredProject == project) focusedScale else 1f
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
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .background(Design.Color.black)
                        .onPointerEvent(PointerEventType.Enter) { hoveredProject = project },
                )
            }
        }
        Spacer(Modifier.height(32.dp))
    }
}
