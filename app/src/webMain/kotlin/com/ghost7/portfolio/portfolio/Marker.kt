package com.ghost7.portfolio.portfolio

data class Marker(
    val year: Int,
    val month: Int,
) {
    companion object {
        fun fromProjects(projects: List<Project>): List<Marker> {
            val startDate = projects.minOfOrNull { it.startDate } ?: return emptyList()
            val endDate = projects.maxOfOrNull { it.endDate } ?: return emptyList()
            if (endDate < startDate) return emptyList()

            val startYear = startDate.year
            val startMonth = startDate.month.ordinal + 1
            val endYear = endDate.year
            val endMonth = endDate.month.ordinal + 1

            val markers = mutableListOf<Marker>().apply {
                var currentYear = startYear
                var currentMonth = startMonth

                while (currentYear < endYear || (currentYear == endYear && currentMonth <= endMonth)) {
                    add(
                        Marker(
                            year = currentYear,
                            month = currentMonth,
                        )
                    )

                    if (currentMonth == 12) {
                        currentYear++
                        currentMonth = 1
                    } else {
                        currentMonth++
                    }
                }
            }

            return markers
        }
    }
}
