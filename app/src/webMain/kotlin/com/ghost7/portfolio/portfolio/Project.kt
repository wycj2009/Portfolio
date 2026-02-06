package com.ghost7.portfolio.portfolio

import androidx.compose.ui.graphics.painter.Painter
import kotlinx.datetime.LocalDate
import kotlin.math.max
import kotlin.math.min

data class Project(
    val logo: Painter,
    val startDate: LocalDate,
    val endDate: LocalDate,
) {
    fun getMarkerIndexRange(markers: List<Marker>): IntRange {
        if (markers.isEmpty()) return IntRange.EMPTY

        val startYear = startDate.year
        val startMonth = startDate.month.ordinal + 1
        val endYear = endDate.year
        val endMonth = endDate.month.ordinal + 1

        val startIndex = markers.indexOfFirst { marker ->
            marker.year == startYear && marker.month == startMonth
        }
        val endIndex = markers.indexOfFirst { marker ->
            marker.year == endYear && marker.month == endMonth
        }

        return if (startIndex == -1 || endIndex == -1) {
            IntRange.EMPTY
        } else {
            min(startIndex, endIndex)..max(startIndex, endIndex)
        }
    }
}
