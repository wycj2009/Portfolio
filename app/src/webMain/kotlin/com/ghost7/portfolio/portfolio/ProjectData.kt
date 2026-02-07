package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.lifetimeLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = "개인 프로젝트 - 라이프타임",
                style = Design.Text.baseStyle.copy(
                    fontSize = 16.sp,
                    color = Design.Color.black,
                ),
            )
        }
        Spacer(Modifier.height(15.dp))
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
                    modifier = Modifier.weight(1f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = """
                JAVA, Firebase(Analytics), SQLite, In-App Billing v3 Library(BillingProcessor), AdMob, AlarmManager, NotificationManager, Material Calendar, AudioManager, AppWidget

                - Firebase의 Analytics와 연동
                - SQLite를 사용해 데이터 저장 및 로드
                - In-App Billing v3 Library(BillingProcessor)로 인앱결제 구현
                - AdMob의 전면광고와 배너광고 삽입
                - AlarmManager와 NotificationManager를 사용해 설정한 시간에 알람 및 알림 푸시
                - Material Calendar로 달력 구성
                - AudioManager로 사운드 효과 삽입
                - AppWidget으로 4가지 위젯 삽입
            """.trimIndent(),
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}
