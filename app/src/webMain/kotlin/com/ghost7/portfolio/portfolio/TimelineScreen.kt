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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.ghost7.portfolio.portfolio.Design.Color.a30
import com.ghost7.portfolio.portfolio.Design.Color.a40
import com.ghost7.portfolio.portfolio.Design.Color.a45
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TimelineScreen() {
    val projects = buildProjects()
    val markers = Marker.fromProjects(projects = projects)
    val projectMarkerIndexRanges = projects.map { it.getMarkerIndexRange(markers) }
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    val markerSpacing = 30.dp
    val dotAndTextColor = Design.Color.gray500
    val focusedDotAndTextColor = Color(0xFF2563EB)
    val yearDotRadius = 10.dp
    val monthDotRadius = 6.dp
    val dotLineStrokeWidth = 4.dp
    val textSpacing = 20.dp
    val yearTextSize = 16.sp
    val monthTextSize = 12.sp
    val projectLogoSpacing = 200.dp
    val projectLogoSize = 60.dp
    val projectLogoLineColor = Design.Color.gray500.a30()
    val focusedProjectLogoLineColor = focusedDotAndTextColor.a40()
    val totalHeight = markerSpacing * (markers.size - 1)
    var hoveredProject: Project? by remember { mutableStateOf(null) }
    val focusedMarkerIndexRange = hoveredProject?.getMarkerIndexRange(markers) ?: IntRange.EMPTY
    val focusedAnimFraction = remember { Animatable(0f) }
    val animScale = 1f + (focusedAnimFraction.value * 0.35f)
    val animAlpha = focusedAnimFraction.value
    val animProjectLogoLineColor = lerp(
        start = projectLogoLineColor,
        stop = focusedProjectLogoLineColor,
        fraction = focusedAnimFraction.value,
    )
    val animDotAndTextColor = lerp(
        start = dotAndTextColor,
        stop = focusedDotAndTextColor,
        fraction = focusedAnimFraction.value,
    )
    val animDotLineStrokeWidth = dotLineStrokeWidth * focusedAnimFraction.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .verticalScrollbar(
                adapter = scrollState.rememberScrollStateVerticalScrollbarAdapter(),
                width = 15.dp,
                innerPadding = PaddingValues(horizontal = 3.dp, vertical = 3.dp),
                color = Design.Color.black.a30(),
                pressedColor = Design.Color.black.a45(),
                dragEnabled = true,
            )
            .disableAutoScrollOnFocus()
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
        var titleHeight by remember { mutableStateOf(0.dp) }

        Column(
            modifier = Modifier.onGloballyPositioned {
                titleHeight = with(density) { it.size.height.toDp() }
            },
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
        }
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
                val isFocused = hoveredProject == projects[index]

                Box(
                    modifier = Modifier
                        .zIndex(0f)
                        .offset(
                            x = if (index % 2 == 0) markerX - lineWidth else markerX,
                            y = markerY - (lineStrokeWidth * 0.5f),
                        )
                        .size(width = lineWidth, height = lineStrokeWidth)
                        .graphicsLayer {
                            scaleY = if (isFocused) animScale else 1f
                            transformOrigin = if (index % 2 == 0) {
                                TransformOrigin(1f, 0.5f)
                            } else {
                                TransformOrigin(0f, 0.5f)
                            }
                        }
                        .background(if (isFocused) animProjectLogoLineColor else projectLogoLineColor)
                )
            }
            // marker, text
            markers.forEachIndexed { index, marker ->
                val isYearMarker = marker.month == 1
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * index
                val isFocused = index in focusedMarkerIndexRange
                val scale = if (isFocused) animScale else 1f
                val color = if (isFocused) animDotAndTextColor else dotAndTextColor

                val dotRadius = if (isYearMarker) yearDotRadius else monthDotRadius
                Box(
                    modifier = Modifier
                        .zIndex(2f)
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
                    modifier = Modifier
                        .zIndex(2f)
                        .offset(
                            x = markerX + textSpacing,
                            y = markerY - (with(density) { textSize.toDp() } * 0.77f),
                        )
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            transformOrigin = TransformOrigin(0f, 0.5f)
                        },
                    text = if (isYearMarker) "${marker.year}년" else "${marker.month}월",
                    style = Design.Text.baseStyle.copy(
                        fontSize = textSize,
                        color = color,
                    ),
                )
            }
            // projectLogo, dotLine
            projects.forEachIndexed { index, project ->
                val markerIndexRange = projectMarkerIndexRanges[index]
                val markerX = maxWidth * 0.5f
                val markerY = markerSpacing * markerIndexRange.first
                val logoX = if (index % 2 == 0) {
                    markerX - projectLogoSpacing - projectLogoSize
                } else {
                    markerX + projectLogoSpacing
                }
                val logoY = markerY - (projectLogoSize * 0.5f)
                val isFocused = hoveredProject == project
                val scale = if (isFocused) animScale else 1f

                Image(
                    modifier = Modifier
                        .zIndex(3f)
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
                        .disableAutoScrollOnFocus()
                        .clickable(
                            interactionSource = null,
                            indication = null,
                            onClick = {},
                        ),
                    painter = project.logo,
                    contentDescription = null,
                )

                if (isFocused) {
                    Box(
                        modifier = Modifier
                            .zIndex(1f)
                            .offset(x = markerX - (animDotLineStrokeWidth * 0.5f), y = markerY)
                            .width(animDotLineStrokeWidth)
                            .height(markerSpacing * markerIndexRange.run { last - first })
                            .alpha(animAlpha)
                            .background(focusedDotAndTextColor)
                    )

                    val contentScrollState = rememberScrollState()
                    var contentWidth by remember { mutableStateOf(Dp.Unspecified) }
                    var contentHeight by remember { mutableStateOf(Dp.Unspecified) }
                    val contentRect = run {
                        val viewportWidth = maxWidth
                        val viewportHeight = with(density) { (scrollState.maxValue + scrollState.viewportSize).toDp() }
                        val logoCenterX = logoX + (projectLogoSize * 0.5f)
                        val logoCenterY = titleHeight + logoY + (projectLogoSize * 0.5f)

                        if (index % 2 == 0) {
                            DpRect(
                                left = logoCenterX - 50.dp - contentWidth,
                                top = logoCenterY - (contentHeight * 0.5f),
                                right = logoCenterX - 50.dp,
                                bottom = logoCenterY + (contentHeight * 0.5f),
                            )
                        } else {
                            DpRect(
                                left = logoCenterX + 50.dp,
                                top = logoCenterY - (contentHeight * 0.5f),
                                right = logoCenterX + 50.dp + contentWidth,
                                bottom = logoCenterY + (contentHeight * 0.5f),
                            )
                        }.let {
                            val diff = it.left.coerceAtLeast(0.dp) - it.left
                            it.copy(left = it.left + diff, right = it.right + diff)
                        }.let {
                            val diff = it.right.coerceAtMost(viewportWidth) - it.right
                            it.copy(left = it.left + diff, right = it.right + diff)
                        }.let {
                            val diff = it.top.coerceAtLeast(0.dp) - it.top
                            it.copy(top = it.top + diff, bottom = it.bottom + diff)
                        }.let {
                            val diff = it.bottom.coerceAtMost(viewportHeight) - it.bottom
                            it.copy(top = it.top + diff, bottom = it.bottom + diff)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .zIndex(4f)
                            .offset(x = contentRect.left, y = -titleHeight + contentRect.top)
                            .widthIn(max = maxWidth)
                            .heightIn(max = with(density) { scrollState.viewportSize.toDp() })
                            .alpha(animAlpha)
                            .background(color = Design.Color.blueGray50, shape = RoundedCornerShape(8.dp))
                            .verticalScroll(contentScrollState)
                            .verticalScrollbar(
                                adapter = contentScrollState.rememberScrollStateVerticalScrollbarAdapter(),
                                width = 15.dp,
                                innerPadding = PaddingValues(horizontal = 3.dp, vertical = 3.dp),
                                color = Design.Color.black.a30(),
                                pressedColor = Design.Color.black.a45(),
                                dragEnabled = true,
                            )
                            .disableAutoScrollOnFocus()
                            .clickable(
                                interactionSource = null,
                                indication = null,
                                onClick = {},
                            )
                            .onGloballyPositioned {
                                contentWidth = with(density) { it.size.width.toDp() }
                                contentHeight = min(
                                    with(density) { it.size.height.toDp() },
                                    with(density) { scrollState.viewportSize.toDp() },
                                )
                            }
                            .padding(horizontal = 15.dp, vertical = 15.dp),
                    ) {
                        project.content()
                    }
                }
            }
        }
        Spacer(Modifier.height(32.dp))
    }
}
