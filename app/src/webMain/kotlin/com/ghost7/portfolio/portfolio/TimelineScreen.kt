package com.ghost7.portfolio.portfolio

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimelineScreen() {
    val projects = buildProjects()
    val markers = Marker.fromProjects(projects = projects)
    val projectMarkerIndexRanges = projects.map { it.getMarkerIndexRange(markers) }
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    val markerSpacing = 30.dp
    val dotAndTextColor = Design.Color.gray500
    val focusedDotAndTextColor = Color(0xFF2563EB)
    val yearDotRadius = 10.dp
    val monthDotRadius = 6.dp
    val textSpacing = 20.dp
    val yearTextSize = 16.sp
    val monthTextSize = 12.sp
    val projectLogoSpacing = 200.dp
    val projectLogoSize = 60.dp
    val projectLogoLineColor = Design.Color.gray300
    val totalHeight = markerSpacing * (markers.size - 1)
    var hoveredProject: Project? by remember { mutableStateOf(null) }
    val focusedMarkerIndexRange = hoveredProject?.getMarkerIndexRange(markers) ?: IntRange.EMPTY
    val focusedAnimFraction = remember { Animatable(0f) }
    val animScale = 1f + (focusedAnimFraction.value * 0.35f)
    val animAlpha = focusedAnimFraction.value
    val animDotAndTextColor = lerp(
        start = dotAndTextColor,
        stop = focusedDotAndTextColor,
        fraction = focusedAnimFraction.value,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .clickable(
                interactionSource = null,
                indication = null,
                onClick = {
                    coroutineScope.launch {
                        hoveredProject = null
                        focusedAnimFraction.snapTo(0f)
                    }
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
            // projectLogoLine
            projectMarkerIndexRanges.forEachIndexed { index, projectMarkerIndexRange ->
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * projectMarkerIndexRange.first
                val lineWidth = projectLogoSpacing + (projectLogoSize * 0.5f)
                val lineStrokeWidth = markers.getOrNull(projectMarkerIndexRange.first)?.let { marker ->
                    if (marker.month == 1) yearDotRadius * 2 else monthDotRadius * 2
                } ?: 0.dp
                val scale = if (hoveredProject == projects[index]) animScale else 1f

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
                        .background(projectLogoLineColor)
                )
            }
            // marker, text
            markers.forEachIndexed { index, marker ->
                val isYearMarker = marker.month == 1
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * index
                val scale = if (index in focusedMarkerIndexRange) animScale else 1f
                val color = if (index in focusedMarkerIndexRange) animDotAndTextColor else dotAndTextColor

                val dotRadius = if (isYearMarker) yearDotRadius else monthDotRadius
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
                        .background(color = color, shape = CircleShape)
                )

                val textSize = if (isYearMarker) yearTextSize else monthTextSize
                Text(
                    text = if (isYearMarker) "${marker.year}년" else "${marker.month}월",
                    style = Design.Text.baseStyle.copy(
                        fontSize = textSize,
                        color = color,
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
            // projectLogo
            projects.forEachIndexed { index, project ->
                val markerIndexRange = projectMarkerIndexRanges[index]
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * markerIndexRange.first
                val scale = if (hoveredProject == project) animScale else 1f
                val logoX = if (index % 2 == 0) {
                    markerX - projectLogoSpacing - projectLogoSize
                } else {
                    markerX + projectLogoSpacing
                }
                val logoY = markerY - (projectLogoSize * 0.5f)

                Image(
                    modifier = Modifier
                        .offset(x = logoX, y = logoY)
                        .size(projectLogoSize)
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .clip(RoundedCornerShape(10.dp))
                        .onPointerEvent(PointerEventType.Enter) {
                            if (hoveredProject != project) {
                                coroutineScope.launch {
                                    hoveredProject = project
                                    focusedAnimFraction.snapTo(0f)
                                    focusedAnimFraction.animateTo(
                                        targetValue = 1f,
                                        animationSpec = tween(
                                            durationMillis = 300,
                                            easing = CubicBezierEasing(0.2f, 0.0f, 0f, 1.0f),
                                        ),
                                    )
                                }
                            }
                        }
                        .clickable(
                            interactionSource = null,
                            indication = null,
                            onClick = {},
                        ),
                    painter = project.logo,
                    contentDescription = null,
                )

                if (hoveredProject == project) {
                    val detailWidth = 220.dp
                    val detailX = if (index % 2 == 0) {
                        logoX - detailWidth - 20.dp
                    } else {
                        logoX + projectLogoSize + 20.dp
                    }

                    Box(
                        modifier = Modifier
                            .offset(x = detailX, y = logoY)
                            .defaultMinSize(minWidth = detailWidth, minHeight = projectLogoSize)
                            .alpha(animAlpha)
                            .background(color = Design.Color.gray400, shape = RoundedCornerShape(12.dp))
                            .clickable(
                                interactionSource = null,
                                indication = null,
                                onClick = {},
                            )
                            .padding(horizontal = 14.dp, vertical = 10.dp),
                    ) {
                        // TODO
                    }
                }
            }
        }
        Spacer(Modifier.height(32.dp))
    }
}

@Composable
private fun buildProjects(): List<Project> {
    return listOf(
        Project(
            logo = Design.Icon.logoLifetime,
            startDate = LocalDate(year = 2017, month = 10, day = 1),
            endDate = LocalDate(year = 2018, month = 2, day = 1),
        ),
        Project(
            logo = Design.Icon.logoGrowskills,
            startDate = LocalDate(year = 2019, month = 5, day = 1),
            endDate = LocalDate(year = 2020, month = 5, day = 1),
        ),
        Project(
            logo = Design.Icon.logoWifion,
            startDate = LocalDate(year = 2020, month = 8, day = 1),
            endDate = LocalDate(year = 2021, month = 8, day = 1),
        ),
        Project(
            logo = Design.Icon.logoDietofhell,
            startDate = LocalDate(year = 2021, month = 3, day = 1),
            endDate = LocalDate(year = 2021, month = 5, day = 1),
        ),
        Project(
            logo = Design.Icon.logoKakaotalk,
            startDate = LocalDate(year = 2021, month = 10, day = 1),
            endDate = LocalDate(year = 2024, month = 6, day = 1),
        ),
        Project(
            logo = Design.Icon.logoChaintodo,
            startDate = LocalDate(year = 2022, month = 8, day = 1),
            endDate = LocalDate(year = 2022, month = 11, day = 1),
        ),
        Project(
            logo = Design.Icon.logoKanana,
            startDate = LocalDate(year = 2024, month = 6, day = 1),
            endDate = LocalDate(year = 2026, month = 2, day = 1),
        ),
    )
}
