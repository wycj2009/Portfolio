package com.ghost7.portfolio.portfolio

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.relocation.BringIntoViewModifierNode

/**  포커스 시 발생하는 자동 스크롤 차단 */
fun Modifier.disableAutoScrollOnFocus(): Modifier = this.then(DisableAutoScrollElement)

private object DisableAutoScrollElement : ModifierNodeElement<DisableAutoScrollElement.DisableAutoScrollNode>() {

    override fun create(): DisableAutoScrollNode = DisableAutoScrollNode()
    override fun update(node: DisableAutoScrollNode) {}
    override fun hashCode(): Int = "DisableAutoScroll".hashCode()
    override fun equals(other: Any?): Boolean = other === this

    class DisableAutoScrollNode : BringIntoViewModifierNode, Modifier.Node() {
        override suspend fun bringIntoView(childCoordinates: LayoutCoordinates, boundsProvider: () -> Rect?) {}
    }
}
