package com.ghost7.portfolio.portfolio

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

fun Modifier.verticalScrollbar(
    adapter: VerticalScrollbarAdapter,
    width: Dp,
    innerPadding: PaddingValues,
    color: Color,
    pressedColor: Color,
    dragEnabled: Boolean,
): Modifier = composed {
    val currentAdapter: VerticalScrollbarAdapter by rememberUpdatedState(adapter)

    if (currentAdapter.viewportHeight() <= 0 || currentAdapter.totalScrollAmount() <= 0) return@composed this

    var modifier: Modifier = this
    val density: Density = LocalDensity.current
    val widthPx: Float = with(density) { width.toPx() }
    val innerPaddingPx: Rect = with(density) {
        Rect(
            left = innerPadding.calculateLeftPadding(LayoutDirection.Ltr).toPx(),
            top = innerPadding.calculateTopPadding().toPx(),
            right = innerPadding.calculateRightPadding(LayoutDirection.Ltr).toPx(),
            bottom = innerPadding.calculateBottomPadding().toPx(),
        )
    }
    val scrollbarRadius: Float = widthPx * 0.5f
    val scrollbarAlpha: Animatable<Float, AnimationVector1D> = remember { Animatable(0f) }
    var isPressed: Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scrollbarAlpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 100,
                easing = LinearEasing,
            ),
        )
    }

    if (dragEnabled) {
        modifier = modifier.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val down: PointerInputChange = awaitFirstDown(requireUnconsumed = false)
                    val downScrollbarRect: Rect = currentAdapter.scrollbarRect(viewportWidth = size.width.toFloat(), scrollbarWidth = widthPx)
                    if (downScrollbarRect.contains(down.position).not()) continue
                    isPressed = true
                    val downPosYInScrollbar: Float = down.position.y - downScrollbarRect.top

                    while (true) {
                        val event: PointerEvent = awaitPointerEvent()
                        val change: PointerInputChange = event.changes.firstOrNull { it.id == down.id } ?: break
                        if (change.pressed.not()) break

                        val curScrollbarRect: Rect = currentAdapter.scrollbarRect(viewportWidth = size.width.toFloat(), scrollbarWidth = widthPx)
                        val curPosYInScrollbar: Float = change.position.y - curScrollbarRect.top
                        val viewportHeight: Float = currentAdapter.viewportHeight().toFloat()
                        val scrollRange: Float = currentAdapter.totalScrollAmount().toFloat() + viewportHeight
                        val scrollDelta: Float = (curPosYInScrollbar - downPosYInScrollbar) * (scrollRange / viewportHeight)
                        currentAdapter.dispatchRawDelta(scrollDelta)
                        change.consume()
                    }

                    isPressed = false
                }
            }
        }
    }
    modifier = modifier.drawWithContent {
        drawContent()
        val scrollbarRect: Rect = currentAdapter.scrollbarRect(viewportWidth = size.width, scrollbarWidth = widthPx)
        drawRoundRect(
            color = (if (!isPressed) color else pressedColor).let { it.copy(alpha = it.alpha * scrollbarAlpha.value) },
            topLeft = Offset(
                x = scrollbarRect.left + innerPaddingPx.left,
                y = scrollbarRect.top + innerPaddingPx.top,
            ),
            size = Size(
                width = scrollbarRect.width - innerPaddingPx.left - innerPaddingPx.right,
                height = scrollbarRect.height - innerPaddingPx.top - innerPaddingPx.bottom,
            ),
            cornerRadius = CornerRadius(x = scrollbarRadius, y = scrollbarRadius),
        )
    }
    return@composed modifier
}

@Composable
fun ScrollState.rememberScrollStateVerticalScrollbarAdapter(): VerticalScrollbarAdapter {
    return remember(this) {
        ScrollStateVerticalScrollbarAdapter(state = this)
    }
}

@Composable
fun LazyListState.rememberLazyColumnVerticalScrollbarAdapter(
    itemHeight: Dp,
    itemSpacing: Dp,
    contentPaddingTop: Dp,
    contentPaddingBottom: Dp,
): VerticalScrollbarAdapter {
    val density: Density = LocalDensity.current
    return remember(this, itemHeight, itemSpacing, contentPaddingTop, contentPaddingBottom) {
        with(density) {
            LazyColumnVerticalScrollbarAdapter(
                state = this@rememberLazyColumnVerticalScrollbarAdapter,
                itemHeightPx = itemHeight.roundToPx(),
                itemSpacingPx = itemSpacing.roundToPx(),
                contentPaddingTopPx = contentPaddingTop.roundToPx(),
                contentPaddingBottomPx = contentPaddingBottom.roundToPx(),
            )
        }
    }
}

@Composable
fun LazyGridState.rememberLazyVerticalGridVerticalScrollbarAdapter(
    itemHeight: Dp,
    itemSpacing: Dp,
    contentPaddingTop: Dp,
    contentPaddingBottom: Dp,
    columnCount: Int,
): VerticalScrollbarAdapter {
    val density: Density = LocalDensity.current
    return remember(this, itemHeight, itemSpacing, contentPaddingTop, contentPaddingBottom, columnCount) {
        with(density) {
            LazyVerticalGridVerticalScrollbarAdapter(
                state = this@rememberLazyVerticalGridVerticalScrollbarAdapter,
                itemHeightPx = itemHeight.roundToPx(),
                itemSpacingPx = itemSpacing.roundToPx(),
                contentPaddingTopPx = contentPaddingTop.roundToPx(),
                contentPaddingBottomPx = contentPaddingBottom.roundToPx(),
                columnCount = columnCount,
            )
        }
    }
}

interface VerticalScrollbarAdapter {
    fun viewportHeight(): Int
    fun totalScrollAmount(): Int
    fun currentScrollAmount(): Int
    fun scrollbarRect(viewportWidth: Float, scrollbarWidth: Float): Rect
    fun dispatchRawDelta(delta: Float)
}

private class ScrollStateVerticalScrollbarAdapter(
    private val state: ScrollState,
) : VerticalScrollbarAdapter {

    override fun viewportHeight(): Int {
        return state.viewportSize
    }

    override fun totalScrollAmount(): Int {
        return state.maxValue
    }

    override fun currentScrollAmount(): Int {
        return state.value
    }

    override fun scrollbarRect(viewportWidth: Float, scrollbarWidth: Float): Rect {
        val viewportHeight: Float = viewportHeight().toFloat()
        val totalScrollAmount: Float = totalScrollAmount().toFloat()
        val currentScrollAmount: Float = currentScrollAmount().toFloat()

        val scrollbarHeight: Float = (viewportHeight / (viewportHeight + totalScrollAmount)) * viewportHeight
        val scrollbarY: Float = currentScrollAmount + ((viewportHeight - scrollbarHeight) * currentScrollAmount / totalScrollAmount)

        return Rect(
            left = viewportWidth - scrollbarWidth,
            top = scrollbarY,
            right = viewportWidth,
            bottom = scrollbarY + scrollbarHeight,
        )
    }

    override fun dispatchRawDelta(delta: Float) {
        state.dispatchRawDelta(delta)
    }
}

private class LazyColumnVerticalScrollbarAdapter(
    private val state: LazyListState,
    private val itemHeightPx: Int,
    private val itemSpacingPx: Int,
    private val contentPaddingTopPx: Int,
    private val contentPaddingBottomPx: Int,
) : VerticalScrollbarAdapter {

    override fun viewportHeight(): Int {
        return state.layoutInfo.viewportSize.height
    }

    override fun totalScrollAmount(): Int {
        val itemCount: Int = state.layoutInfo.totalItemsCount
        val totalItemsHeight: Int = (itemCount * itemHeightPx) + ((itemCount - 1) * itemSpacingPx)
        val totalContentHeight: Int = contentPaddingTopPx + totalItemsHeight + contentPaddingBottomPx
        return (totalContentHeight - viewportHeight()).coerceAtLeast(0)
    }

    override fun currentScrollAmount(): Int {
        val firstVisibleItemIndex: Int = state.firstVisibleItemIndex
        val beforeItemsHeight: Int = (firstVisibleItemIndex * itemHeightPx) + (firstVisibleItemIndex * itemSpacingPx)
        return (beforeItemsHeight + state.firstVisibleItemScrollOffset).coerceAtLeast(0)
    }

    override fun scrollbarRect(viewportWidth: Float, scrollbarWidth: Float): Rect {
        val viewportHeight: Float = viewportHeight().toFloat()
        val totalScrollAmount: Float = totalScrollAmount().toFloat()
        val currentScrollAmount: Float = currentScrollAmount().toFloat()

        val scrollbarHeight: Float = (viewportHeight / (viewportHeight + totalScrollAmount)) * viewportHeight
        val scrollbarY: Float = (viewportHeight - scrollbarHeight) * currentScrollAmount / totalScrollAmount

        return Rect(
            left = viewportWidth - scrollbarWidth,
            top = scrollbarY,
            right = viewportWidth,
            bottom = scrollbarY + scrollbarHeight,
        )
    }

    override fun dispatchRawDelta(delta: Float) {
        state.dispatchRawDelta(delta)
    }
}

private class LazyVerticalGridVerticalScrollbarAdapter(
    private val state: LazyGridState,
    private val itemHeightPx: Int,
    private val itemSpacingPx: Int,
    private val contentPaddingTopPx: Int,
    private val contentPaddingBottomPx: Int,
    private val columnCount: Int,
) : VerticalScrollbarAdapter {

    override fun viewportHeight(): Int {
        return state.layoutInfo.viewportSize.height
    }

    override fun totalScrollAmount(): Int {
        val itemCount: Int = state.layoutInfo.totalItemsCount
        val rowCount: Int = (itemCount + columnCount - 1) / columnCount
        val totalItemsHeight: Int = (rowCount * itemHeightPx) + ((rowCount - 1) * itemSpacingPx)
        val totalContentHeight: Int = contentPaddingTopPx + totalItemsHeight + contentPaddingBottomPx
        return (totalContentHeight - viewportHeight()).coerceAtLeast(0)
    }

    override fun currentScrollAmount(): Int {
        val firstVisibleItemIndex: Int = state.firstVisibleItemIndex
        val firstVisibleRowIndex: Int = firstVisibleItemIndex / columnCount
        val beforeItemsHeight: Int = (firstVisibleRowIndex * itemHeightPx) + (firstVisibleRowIndex * itemSpacingPx)
        return (beforeItemsHeight + state.firstVisibleItemScrollOffset).coerceAtLeast(0)
    }

    override fun scrollbarRect(viewportWidth: Float, scrollbarWidth: Float): Rect {
        val viewportHeight: Float = viewportHeight().toFloat()
        val totalScrollAmount: Float = totalScrollAmount().toFloat()
        val currentScrollAmount: Float = currentScrollAmount().toFloat()

        val scrollbarHeight: Float = (viewportHeight / (viewportHeight + totalScrollAmount)) * viewportHeight
        val scrollbarY: Float = (viewportHeight - scrollbarHeight) * currentScrollAmount / totalScrollAmount

        return Rect(
            left = viewportWidth - scrollbarWidth,
            top = scrollbarY,
            right = viewportWidth,
            bottom = scrollbarY + scrollbarHeight,
        )
    }

    override fun dispatchRawDelta(delta: Float) {
        state.dispatchRawDelta(delta)
    }
}
