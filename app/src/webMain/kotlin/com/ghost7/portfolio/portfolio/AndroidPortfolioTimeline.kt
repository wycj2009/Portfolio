package com.ghost7.portfolio.portfolio

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

// 프로젝트 데이터 클래스
data class Project(
    val id: String,
    val name: String,
    val description: String,
    val startDate: Long, // timestamp (milliseconds)
    val endDate: Long,   // timestamp (milliseconds)
    val technologies: List<String>,
    val role: String,
    val icon: String,
    val color: Color
)

// 타임 마커 데이터 클래스
data class TimeMarker(
    val timestamp: Long,
    val year: Int,
    val month: Int,
    val isYear: Boolean
)

// 날짜 유틸리티 함수들
object DateUtils {
    fun createTimestamp(year: Int, month: Int, day: Int = 1): Long {
        // 간단한 timestamp 계산 (1970년 1월 1일부터의 일수)
        val daysFrom1970 = (year - 1970) * 365 + (year - 1970) / 4 +
            getMonthDays(year, month) + day - 1
        return daysFrom1970 * 24L * 60L * 60L * 1000L
    }

    private fun getMonthDays(year: Int, month: Int): Int {
        val daysInMonth = listOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        var days = 0
        for (m in 1 until month) {
            days += daysInMonth[m]
            if (m == 2 && isLeapYear(year)) days++
        }
        return days
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }

    fun formatDate(timestamp: Long): String {
        val year = getYear(timestamp)
        val month = getMonth(timestamp)
        val day = getDay(timestamp)
        return "${year}년 ${month}월 ${day}일"
    }

    fun formatYearMonth(year: Int, month: Int, isYear: Boolean): String {
        return if (isYear) "${year}년" else "${month}월"
    }

    private fun getYear(timestamp: Long): Int {
        val days = timestamp / (24L * 60L * 60L * 1000L)
        return 1970 + (days / 365).toInt()
    }

    private fun getMonth(timestamp: Long): Int {
        val year = getYear(timestamp)
        val yearStart = createTimestamp(year, 1, 1)
        val daysInYear = ((timestamp - yearStart) / (24L * 60L * 60L * 1000L)).toInt()

        val daysInMonth = listOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        var cumDays = 0
        for (m in 1..12) {
            val monthDays = if (m == 2 && isLeapYear(year)) 29 else daysInMonth[m - 1]
            if (daysInYear < cumDays + monthDays) return m
            cumDays += monthDays
        }
        return 12
    }

    private fun getDay(timestamp: Long): Int {
        val year = getYear(timestamp)
        val month = getMonth(timestamp)
        val monthStart = createTimestamp(year, month, 1)
        return ((timestamp - monthStart) / (24L * 60L * 60L * 1000L)).toInt() + 1
    }

    fun getDurationMonths(startTimestamp: Long, endTimestamp: Long): Int {
        val diff = endTimestamp - startTimestamp
        return (diff / (30L * 24L * 60L * 60L * 1000L)).toInt()
    }
}

@Composable
fun PortfolioTimelineScreen() {
    // 샘플 프로젝트 데이터
    val projects = remember {
        listOf(
            Project(
                id = "1",
                name = "이커머스 쇼핑 앱",
                description = "대형 이커머스 플랫폼의 안드로이드 앱 개발 및 유지보수",
                startDate = DateUtils.createTimestamp(2023, 1, 1),
                endDate = DateUtils.createTimestamp(2023, 12, 31),
                technologies = listOf("Kotlin", "Jetpack Compose", "MVVM", "Retrofit", "Room"),
                role = "Android Developer",
                icon = "🛒",
                color = Color(0xFF4F46E5)
            ),
            Project(
                id = "2",
                name = "금융 뱅킹 앱",
                description = "모바일 뱅킹 서비스의 핵심 기능 개발 및 보안 강화",
                startDate = DateUtils.createTimestamp(2024, 2, 1),
                endDate = DateUtils.createTimestamp(2024, 9, 30),
                technologies = listOf("Kotlin", "Coroutines", "Hilt", "Firebase", "BiometricAPI"),
                role = "Senior Developer",
                icon = "💳",
                color = Color(0xFF059669)
            ),
            Project(
                id = "3",
                name = "소셜 미디어 플랫폼",
                description = "실시간 채팅 및 미디어 공유 기능을 포함한 소셜 앱 개발",
                startDate = DateUtils.createTimestamp(2024, 10, 1),
                endDate = DateUtils.createTimestamp(2025, 3, 28),
                technologies = listOf("Kotlin", "Jetpack Compose", "WebSocket", "CameraX", "Coil"),
                role = "Lead Developer",
                icon = "💬",
                color = Color(0xFFDC2626)
            ),
            Project(
                id = "4",
                name = "헬스케어 트래킹 앱",
                description = "건강 데이터 수집 및 분석을 위한 웨어러블 연동 앱",
                startDate = DateUtils.createTimestamp(2025, 4, 1),
                endDate = DateUtils.createTimestamp(2025, 10, 30),
                technologies = listOf("Kotlin", "Wear OS", "Health Connect", "ML Kit", "Charts"),
                role = "Android Developer",
                icon = "❤️",
                color = Color(0xFFEC4899)
            ),
            Project(
                id = "5",
                name = "배달 서비스 앱",
                description = "실시간 위치 추적 및 주문 관리 시스템 구축",
                startDate = DateUtils.createTimestamp(2025, 11, 1),
                endDate = DateUtils.createTimestamp(2026, 2, 28),
                technologies = listOf("Kotlin", "Google Maps", "FCM", "WorkManager", "DataStore"),
                role = "Senior Developer",
                icon = "🚚",
                color = Color(0xFFF59E0B)
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF9FAFB),
                        Color(0xFFEFF6FF),
                        Color(0xFFFAF5FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 헤더
            Header()

            Spacer(modifier = Modifier.height(64.dp))

            // 타임라인
            Timeline(projects = projects)
        }
    }
}

@Composable
fun Header() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Android Developer Portfolio",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineLarge.copy(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF2563EB),
                        Color(0xFF9333EA),
                        Color(0xFFDB2777)
                    )
                )
            ).merge(Design.Text.baseStyle)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "프로젝트 타임라인 - 아이콘을 탭해보세요",
            fontSize = 18.sp,
            color = Color(0xFF6B7280),
            style = Design.Text.baseStyle
        )
    }
}

@Composable
fun Timeline(projects: List<Project>) {
    var selectedProjectId by remember { mutableStateOf<String?>(null) }

    // 타임라인 범위 계산
    val minDate = remember(projects) {
        projects.minByOrNull { it.startDate }?.startDate ?: 0L
    }
    val maxDate = remember(projects) {
        projects.maxByOrNull { it.endDate }?.endDate ?: 0L
    }
    val totalDuration = remember(minDate, maxDate) {
        maxDate - minDate
    }

    // 타임 마커 생성 (년-월 단위)
    val timeMarkers = remember(minDate, maxDate) {
        generateTimeMarkers(minDate, maxDate)
    }

    val selectedProject = projects.find { it.id == selectedProjectId }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1200.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // 왼쪽 프로젝트 영역
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    projects.forEachIndexed { index, project ->
                        if (index % 2 == 0) {
                            ProjectItem(
                                project = project,
                                isLeft = true,
                                isSelected = selectedProjectId == project.id,
                                minDate = minDate,
                                maxDate = maxDate,
                                totalDuration = totalDuration,
                                onClick = {
                                    selectedProjectId = if (selectedProjectId == project.id) null else project.id
                                }
                            )
                        }
                    }
                }
            }

            // 중앙 타임라인
            Box(
                modifier = Modifier
                    .width(128.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.TopCenter
            ) {
                // 중앙 선
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .fillMaxHeight()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF3B82F6),
                                    Color(0xFFA855F7),
                                    Color(0xFFEC4899)
                                )
                            ),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .shadow(8.dp)
                )

                // 타임 마커들
                Box(modifier = Modifier.fillMaxSize()) {
                    timeMarkers.forEach { marker ->
                        TimeMarkerItem(
                            marker = marker,
                            minDate = minDate,
                            totalDuration = totalDuration,
                            selectedProject = selectedProject
                        )
                    }
                }

                // 프로젝트 연결선과 기간 바
                Box(modifier = Modifier.fillMaxSize()) {
                    projects.forEach { project ->
                        ProjectConnectionLine(
                            project = project,
                            minDate = minDate,
                            totalDuration = totalDuration,
                            isSelected = selectedProjectId == project.id
                        )
                    }
                }
            }

            // 오른쪽 프로젝트 영역
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    projects.forEachIndexed { index, project ->
                        if (index % 2 == 1) {
                            ProjectItem(
                                project = project,
                                isLeft = false,
                                isSelected = selectedProjectId == project.id,
                                minDate = minDate,
                                maxDate = maxDate,
                                totalDuration = totalDuration,
                                onClick = {
                                    selectedProjectId = if (selectedProjectId == project.id) null else project.id
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BoxScope.TimeMarkerItem(
    marker: TimeMarker,
    minDate: Long,
    totalDuration: Long,
    selectedProject: Project?
) {
    val position = remember(marker, minDate, totalDuration) {
        ((marker.timestamp - minDate).toFloat() / totalDuration.toFloat())
    }

    val isInRange = selectedProject?.let {
        marker.timestamp >= it.startDate && marker.timestamp <= it.endDate
    } ?: false

    val scale by animateFloatAsState(
        targetValue = if (isInRange) 1.3f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "marker_scale"
    )

    val markerColor by animateColorAsState(
        targetValue = if (isInRange && selectedProject != null) {
            selectedProject.color
        } else if (marker.isYear) {
            Color(0xFF2563EB)
        } else {
            Color(0xFF9CA3AF)
        },
        animationSpec = tween(200),
        label = "marker_color"
    )

    val textColor by animateColorAsState(
        targetValue = if (isInRange && selectedProject != null) {
            selectedProject.color
        } else {
            Color.Unspecified
        },
        animationSpec = tween(200),
        label = "text_color"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .offset(y = (position * 1200).dp)
            .scale(scale),
        contentAlignment = Alignment.Center
    ) {
        // 마커 점
        Box(
            modifier = Modifier
                .size(if (marker.isYear) 16.dp else 10.dp)
                .background(markerColor, CircleShape)
                .shadow(4.dp, CircleShape)
                .border(
                    width = if (marker.isYear) 4.dp else 2.dp,
                    color = if (marker.isYear) Color(0xFFBFDBFE) else Color(0xFFF3F4F6),
                    shape = CircleShape
                )
        )

        // 양쪽 수평선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (marker.isYear) 2.dp else 1.dp)
                .background(Color(0xFFE5E7EB))
        )

        val dateText = DateUtils.formatYearMonth(marker.year, marker.month, marker.isYear)

        // 왼쪽 날짜
        Text(
            text = dateText,
            fontSize = if (marker.isYear) 16.sp else 12.sp,
            fontWeight = if (marker.isYear) FontWeight.Bold else FontWeight.Normal,
            color = textColor.takeIf { it != Color.Unspecified } ?: if (marker.isYear) {
                Color(0xFF111827)
            } else {
                Color(0xFF6B7280)
            },
            style = Design.Text.baseStyle,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(end = 48.dp)
        )

        // 오른쪽 날짜
        Text(
            text = dateText,
            fontSize = if (marker.isYear) 16.sp else 12.sp,
            fontWeight = if (marker.isYear) FontWeight.Bold else FontWeight.Normal,
            color = textColor.takeIf { it != Color.Unspecified } ?: if (marker.isYear) {
                Color(0xFF111827)
            } else {
                Color(0xFF6B7280)
            },
            style = Design.Text.baseStyle,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(start = 48.dp)
        )
    }
}

@Composable
fun BoxScope.ProjectConnectionLine(
    project: Project,
    minDate: Long,
    totalDuration: Long,
    isSelected: Boolean
) {
    val startPosition = remember(project, minDate, totalDuration) {
        ((project.startDate - minDate).toFloat() / totalDuration.toFloat())
    }
    val endPosition = remember(project, minDate, totalDuration) {
        ((project.endDate - minDate).toFloat() / totalDuration.toFloat())
    }
    val height = remember(startPosition, endPosition) {
        (endPosition - startPosition) * 1200
    }

    val barWidth by animateDpAsState(
        targetValue = if (isSelected) 8.dp else 4.dp,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "bar_width"
    )

    val barAlpha by animateFloatAsState(
        targetValue = if (isSelected) 0.8f else 0.3f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "bar_alpha"
    )

    // 프로젝트 기간 바
    Box(
        modifier = Modifier
            .width(barWidth)
            .height(height.dp)
            .align(Alignment.TopCenter)
            .offset(y = (startPosition * 1200).dp)
            .background(
                project.color.copy(alpha = barAlpha),
                RoundedCornerShape(barWidth / 2)
            )
    )
}

@Composable
fun BoxScope.ProjectItem(
    project: Project,
    isLeft: Boolean,
    isSelected: Boolean,
    minDate: Long,
    maxDate: Long,
    totalDuration: Long,
    onClick: () -> Unit
) {
    val position = remember(project, minDate, totalDuration) {
        ((project.startDate - minDate).toFloat() / totalDuration.toFloat())
    }

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.25f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMedium),
        label = "project_scale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter)
            .offset(y = (position * 1200).dp)
    ) {
        // 프로젝트 아이콘 (고정 위치)
        Box(
            modifier = Modifier
                .align(if (isLeft) Alignment.CenterEnd else Alignment.CenterStart)
                .padding(horizontal = 16.dp)
                .size(56.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .shadow(12.dp, RoundedCornerShape(16.dp))
                .background(project.color, RoundedCornerShape(16.dp))
                .border(3.dp, Color.White, RoundedCornerShape(16.dp))
                .clickable(onClick = onClick)
                .zIndex(10f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = project.icon,
                fontSize = 28.sp,
                style = Design.Text.baseStyle
            )
        }

        // 상세 정보 카드 (독립 위치)
        AnimatedVisibility(
            visible = isSelected,
            modifier = Modifier
                .align(if (isLeft) Alignment.CenterEnd else Alignment.CenterStart)
                .padding(
                    end = if (isLeft) 88.dp else 0.dp,
                    start = if (isLeft) 0.dp else 88.dp
                ),
            enter = fadeIn() + expandHorizontally(
                expandFrom = if (isLeft) Alignment.End else Alignment.Start
            ) + scaleIn(),
            exit = fadeOut() + shrinkHorizontally(
                shrinkTowards = if (isLeft) Alignment.End else Alignment.Start
            ) + scaleOut()
        ) {
            ProjectDetailCard(
                project = project,
                isLeft = isLeft,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ProjectDetailCard(
    project: Project,
    isLeft: Boolean,
    modifier: Modifier = Modifier
) {
    val duration = remember(project) {
        DateUtils.getDurationMonths(project.startDate, project.endDate)
    }

    Card(
        modifier = modifier
            .width(384.dp)
            .shadow(24.dp, RoundedCornerShape(16.dp))
            .zIndex(40f),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(2.dp, project.color)
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            // 헤더
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(project.color, RoundedCornerShape(12.dp))
                            .shadow(4.dp, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = project.icon, fontSize = 24.sp, style = Design.Text.baseStyle)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = project.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF111827),
                            style = Design.Text.baseStyle
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = project.color.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = project.role,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = project.color,
                        style = Design.Text.baseStyle,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 설명
            Text(
                text = project.description,
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
                lineHeight = 20.sp,
                style = Design.Text.baseStyle
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 기간 정보
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFF9FAFB)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = "📅",
                        fontSize = 16.sp,
                        style = Design.Text.baseStyle,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Column {
                        Text(
                            text = "${DateUtils.formatDate(project.startDate)} - ${DateUtils.formatDate(project.endDate)}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF374151),
                            style = Design.Text.baseStyle
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "총 ${duration}개월",
                            fontSize = 12.sp,
                            color = Color(0xFF6B7280),
                            style = Design.Text.baseStyle
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 기술 스택
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "💻",
                    fontSize = 16.sp,
                    style = Design.Text.baseStyle,
                    modifier = Modifier.padding(end = 8.dp, top = 4.dp)
                )
                Column {
                    project.technologies.chunked(3).forEach { rowTechs ->
                        Row(
                            modifier = Modifier.padding(bottom = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            rowTechs.forEach { tech ->
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = project.color.copy(alpha = 0.1f),
                                    border = BorderStroke(1.dp, project.color.copy(alpha = 0.3f))
                                ) {
                                    Text(
                                        text = tech,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = project.color,
                                        style = Design.Text.baseStyle,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// 타임 마커 생성 함수
fun generateTimeMarkers(minDate: Long, maxDate: Long): List<TimeMarker> {
    val markers = mutableListOf<TimeMarker>()

    // 시작 년도와 월 계산
    val startYear = 1970 + ((minDate / (365L * 24L * 60L * 60L * 1000L))).toInt()
    val endYear = 1970 + ((maxDate / (365L * 24L * 60L * 60L * 1000L))).toInt() + 1

    for (year in startYear..endYear) {
        for (month in 1..12) {
            val timestamp = DateUtils.createTimestamp(year, month, 1)
            if (timestamp >= minDate && timestamp <= maxDate) {
                markers.add(
                    TimeMarker(
                        timestamp = timestamp,
                        year = year,
                        month = month,
                        isYear = month == 1
                    )
                )
            }
        }
    }

    return markers
}
