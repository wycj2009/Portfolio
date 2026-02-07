package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDate

@Composable
fun buildProjects(): List<Project> {
    return listOf(
        Project(
            logo = Design.Resource.lifetimeLogo,
            startDate = LocalDate(year = 2017, month = 10, day = 1),
            endDate = LocalDate(year = 2018, month = 5, day = 1),
            content = { lifetimeContent() },
        ),
        Project(
            logo = Design.Resource.growskillsLogo,
            startDate = LocalDate(year = 2019, month = 5, day = 1),
            endDate = LocalDate(year = 2020, month = 5, day = 1),
            content = { lifetimeContent() },
        ),
        Project(
            logo = Design.Resource.wifionLogo,
            startDate = LocalDate(year = 2020, month = 8, day = 1),
            endDate = LocalDate(year = 2021, month = 8, day = 1),
            content = { lifetimeContent() },
        ),
        Project(
            logo = Design.Resource.dietofhellLogo,
            startDate = LocalDate(year = 2021, month = 3, day = 1),
            endDate = LocalDate(year = 2021, month = 5, day = 1),
            content = { lifetimeContent() },
        ),
        Project(
            logo = Design.Resource.kakaotalkLogo,
            startDate = LocalDate(year = 2021, month = 10, day = 1),
            endDate = LocalDate(year = 2024, month = 6, day = 1),
            content = { lifetimeContent() },
        ),
        Project(
            logo = Design.Resource.chaintodoLogo,
            startDate = LocalDate(year = 2022, month = 8, day = 1),
            endDate = LocalDate(year = 2022, month = 11, day = 1),
            content = { lifetimeContent() },
        ),
        Project(
            logo = Design.Resource.kananaLogo,
            startDate = LocalDate(year = 2024, month = 6, day = 1),
            endDate = LocalDate(year = 2026, month = 2, day = 1),
            content = { lifetimeContent() },
        ),
        Project(
            logo = Design.Resource.artsharehubLogo,
            startDate = LocalDate(year = 2025, month = 12, day = 1),
            endDate = LocalDate(year = 2026, month = 2, day = 1),
            content = { lifetimeContent() },
        ),
    )
}

@Composable
private fun lifetimeContent() {
    Column(modifier = Modifier.width(800.dp)) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 4,
        ) {
            listOf(
                Design.Resource.lifetimeScreenshot0,
                Design.Resource.lifetimeScreenshot1,
                Design.Resource.lifetimeScreenshot2,
                Design.Resource.lifetimeScreenshot3,
                Design.Resource.lifetimeScreenshot4,
                Design.Resource.lifetimeScreenshot5,
                Design.Resource.lifetimeScreenshot6,
                Design.Resource.lifetimeScreenshot7,
            ).forEach { painter ->
                Image(
                    modifier = Modifier.width(200.dp),
                    painter = painter,
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                )
            }
        }
    }
}
