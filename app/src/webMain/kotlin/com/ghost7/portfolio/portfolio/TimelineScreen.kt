package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimelineScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Spacer(modifier = Modifier.height(32.dp))
        Timeline()

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
private fun Timeline() {
    Box(
        modifier = Modifier.height(5000.dp).width(10.dp).background(Design.Color.gray200)
    )
}
