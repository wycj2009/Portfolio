package com.ghost7.portfolio.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
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
            content = { growskillsContent() },
        ),
        Project(
            logo = Design.Resource.wifionLogo,
            startDate = LocalDate(year = 2020, month = 8, day = 1),
            endDate = LocalDate(year = 2021, month = 8, day = 1),
            content = { wifionContent() },
        ),
        Project(
            logo = Design.Resource.dietofhellLogo,
            startDate = LocalDate(year = 2021, month = 3, day = 1),
            endDate = LocalDate(year = 2021, month = 5, day = 1),
            content = { dietofhellContent() },
        ),
        Project(
            logo = Design.Resource.kakaotalkLogo,
            startDate = LocalDate(year = 2021, month = 10, day = 1),
            endDate = LocalDate(year = 2024, month = 6, day = 1),
            content = { kakaotalkContent() },
        ),
        Project(
            logo = Design.Resource.chaintodoLogo,
            startDate = LocalDate(year = 2022, month = 8, day = 1),
            endDate = LocalDate(year = 2022, month = 11, day = 1),
            content = { chaintodoContent() },
        ),
        Project(
            logo = Design.Resource.kananaLogo,
            startDate = LocalDate(year = 2024, month = 6, day = 1),
            endDate = LocalDate(year = 2026, month = 2, day = 1),
            content = { kananaContent() },
        ),
        Project(
            logo = Design.Resource.artsharehubLogo,
            startDate = LocalDate(year = 2025, month = 12, day = 1),
            endDate = LocalDate(year = 2026, month = 2, day = 1),
            content = { artsharehubContent() },
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
                text = buildAnnotatedString {
                    append("개인 프로젝트 - 라이프타임")
                },
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
            horizontalArrangement = Arrangement.Center,
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
                    modifier = Modifier.fillMaxWidth(0.249f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        Android Studio, Java, Firebase(Analytics), SQLite, In-App Billing v3 Library(BillingProcessor), AdMob, AlarmManager, NotificationManager, Material Calendar, AudioManager, AppWidget
        
                        - Firebase의 Analytics와 연동
                        - SQLite를 사용해 데이터 저장 및 로드
                        - In-App Billing v3 Library(BillingProcessor)로 인앱결제 구현
                        - AdMob의 전면광고와 배너광고 삽입
                        - AlarmManager와 NotificationManager를 사용해 설정한 시간에 알람 및 알림 푸시
                        - Material Calendar로 달력 구성
                        - AudioManager로 사운드 효과 삽입
                        - AppWidget으로 4가지 위젯 삽입
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}

@Composable
private fun growskillsContent() {
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.growskillsLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    append("개인 프로젝트 - 스킬 키우기")
                },
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
            horizontalArrangement = Arrangement.Center,
        ) {
            listOf(
                Design.Resource.growskillsScreenshot0,
                Design.Resource.growskillsScreenshot1,
                Design.Resource.growskillsScreenshot2,
                Design.Resource.growskillsScreenshot3,
                Design.Resource.growskillsScreenshot4,
                Design.Resource.growskillsScreenshot5,
                Design.Resource.growskillsScreenshot6,
                Design.Resource.growskillsScreenshot7,
            ).forEach { painter ->
                Image(
                    modifier = Modifier.fillMaxWidth(0.249f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        Unity, C#, Firebase(Analytics, Authentication, Realtime Database, Storage), PlayGameServices, REST API, Coroutine, Json, IAP, AdMob, Particle System, Animation
        
                        - Firebase의 Authentication 토큰을 가져와 PlayGameServices와 연동하여 로그인 구현
                        - Firebase의 Realtime Database를 사용해 실시간 채팅 구현. Firebase의 Storage를 사용해 서버에 유저 데이터 저장
                        - IAP를 사용해 인앱결제 구현
                        - AdMob의 보상형 동영상 광고 삽입
                        - Particle System을 사용해 스킬 이펙트 구현
                        - Animation을 사용해 캐릭터 움직임 효과 구현
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}

@Composable
private fun wifionContent() {
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.wifionLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    append("넥스컨텔레컴 - 와이파이온")
                },
                style = Design.Text.baseStyle.copy(
                    fontSize = 16.sp,
                    color = Design.Color.black,
                ),
            )
        }
        Spacer(Modifier.height(15.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.Center,
        ) {
            listOf(
                Design.Resource.wifionScreenshot0,
                Design.Resource.wifionScreenshot1,
                Design.Resource.wifionScreenshot2,
                Design.Resource.wifionScreenshot3,
                Design.Resource.wifionScreenshot4,
                Design.Resource.wifionScreenshot5,
            ).forEach { painter ->
                Image(
                    modifier = Modifier.fillMaxWidth(0.249f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        Android Studio, Kotlin, Java, Firebase(Analytics, Cloud Messaging), Retrofit2, REST API, Coroutine, Json, WifiManager, ConnectivityManager, ForegroundService, NotificationManager, Google Map, AES, Material Design, Zeplin
                        
                        - Firebase와 연동하여 Cloud Messaging 기능 구현
                        - Retrofit2를 사용하여 REST API를 구현 및 웹 서버와 통신
                        - WifiManager로 와이파이 정보를 가져오고 고유값인 BSSID로 와이파이를 식별하며 서버에서 받아온 와이파이 리스트를 추천 목록에 업데이트
                        - ConnectivityManager를 사용하여 와이파이 상태변화 체크
                        - 와이파이 관련 기능들은 ForegroundService 위에서 동작
                        - NotificationManager를 사용해 와이파이가 연결되었을 때 알림 창 푸시
                        - Google Map과 연동하여 주변 와이파이 위치 표시
                        - AES(고급 암호화 표준) 암호화 기법으로 와이파이 정보 암호화 및 복호화
                        - Material Design 가이드라인 참고
                        - Zeplin을 사용해 디자이너와 협업
                        - 초기 버전은 JAVA로 개발했으며 Kotlin으로 리팩토링하여 업데이트
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}

@Composable
private fun dietofhellContent() {
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.dietofhellLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    append("개인 프로젝트 - 지옥의 다이어트")
                },
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
            horizontalArrangement = Arrangement.Center,
        ) {
            listOf(
                Design.Resource.dietofhellScreenshot0,
                Design.Resource.dietofhellScreenshot1,
                Design.Resource.dietofhellScreenshot2,
                Design.Resource.dietofhellScreenshot3,
                Design.Resource.dietofhellScreenshot4,
                Design.Resource.dietofhellScreenshot5,
                Design.Resource.dietofhellScreenshot6,
                Design.Resource.dietofhellScreenshot7,
            ).forEach { painter ->
                Image(
                    modifier = Modifier.fillMaxWidth(0.249f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        Android Studio, Kotlin, Coroutine, Gson, BillingClient, AdMob, ForegroundService, AlarmManager, NotificationManager, Sensor, Custom View(Bar Chart, Line Chart), Material Design
                        
                        - BillingClient를 사용해 인앱결제 구현
                        - AdMob과 연동하여 전면광고, 보상형 광고, 네이티브 광고 삽입
                        - ForegroundService로 상단 알림이 고정되며 다이어트 일정 정보 디스플레이
                        - AlarmManager와 NotificationManager로 지정된 시간에 체중측정 및 식사 알림 푸시
                        - Sensor를 사용해 걸음 수 측정
                        - Bar Chart와 Line Chart는 Custom View로 만들었으며 데이터의 성격에 따라 세부 설정값 세팅
                        - Material Design에 기반한 디자인
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}

@Composable
private fun kakaotalkContent() {
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.kakaotalkLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    append("카카오 - 카카오톡")
                },
                style = Design.Text.baseStyle.copy(
                    fontSize = 16.sp,
                    color = Design.Color.black,
                ),
            )
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        ???
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}

@Composable
private fun chaintodoContent() {
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.chaintodoLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    append("개인 프로젝트 - 체인 투두")
                    append("   ")
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://play.google.com/store/apps/details?id=com.ghost7.chaintodo&pcampaignid=web_share",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = Color(0xFF2563EB),
                                    textDecoration = TextDecoration.Underline,
                                ),
                            ),
                        )
                    ) {
                        append("Google Play")
                    }
                },
                style = Design.Text.baseStyle.copy(
                    fontSize = 16.sp,
                    color = Design.Color.black,
                ),
            )
        }
        Spacer(Modifier.height(15.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.Center,
        ) {
            listOf(
                Design.Resource.chaintodoScreenshot0,
                Design.Resource.chaintodoScreenshot1,
                Design.Resource.chaintodoScreenshot2,
                Design.Resource.chaintodoScreenshot3,
                Design.Resource.chaintodoScreenshot4,
            ).forEach { painter ->
                Image(
                    modifier = Modifier.fillMaxWidth(0.249f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        Android Studio, Kotlin, Coroutine/Flow, Hilt, Room, DataStore, Retrofit2, Gson, BillingClient, AdMob(Interstitial), ViewBinding, Custom View(CalendarView), Material Design
                        
                        - Clean Architecture(data/domain/presentation) + UseCase 구조로 투두/캘린더 데이터 관리
                        - 계층형 Todo 구조(idxPath) 지원 및 드래그 정렬(ItemTouchHelper) 구현
                        - ViewPager 기반 CalendarView 커스텀 구현: 월 단위 페이지 전환, 도트/기간 라인 표시, 공휴일 색상 강조
                        - Google Calendar API 연동으로 공휴일/휴일 데이터 수집 및 로컬 저장
                        - DataStore로 텍스트 크기 등 설정 유지, 설정 화면에서 데이터 초기화/내보내기/가져오기 제공
                        - BillingClient로 광고 제거 인앱결제 구현 및 구매 처리(Acknowledge) 로직 구성
                        - AdMob 전면 광고 로드/노출(Interstitial) 연동
                        - 다국어 리소스(ko/ja) 및 Material Design 테마 적용
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}

@Composable
private fun kananaContent() {
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.kananaLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    append("카카오 - 카나나")
                    append("   ")
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://play.google.com/store/apps/details?id=com.kakao.kanana&pcampaignid=web_share",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = Color(0xFF2563EB),
                                    textDecoration = TextDecoration.Underline,
                                ),
                            ),
                        )
                    ) {
                        append("Google Play")
                    }
                },
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
            horizontalArrangement = Arrangement.Center,
        ) {
            listOf(
                Design.Resource.kananaScreenshot0,
                Design.Resource.kananaScreenshot1,
                Design.Resource.kananaScreenshot2,
                Design.Resource.kananaScreenshot3,
                Design.Resource.kananaScreenshot4,
                Design.Resource.kananaScreenshot5,
                Design.Resource.kananaScreenshot6,
            ).forEach { painter ->
                Image(
                    modifier = Modifier.fillMaxWidth(0.249f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        ???
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}

@Composable
private fun artsharehubContent() {
    Column(modifier = Modifier.width(900.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(10.dp)),
                painter = Design.Resource.artsharehubLogo,
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Spacer(Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    append("개인 프로젝트 - 공연전시나눔터")
                    append("   ")
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://art-share-hub.web.app",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = Color(0xFF2563EB),
                                    textDecoration = TextDecoration.Underline,
                                ),
                            ),
                        )
                    ) {
                        append("https://art-share-hub.web.app")
                    }
                },
                style = Design.Text.baseStyle.copy(
                    fontSize = 16.sp,
                    color = Design.Color.black,
                ),
            )
        }
        Spacer(Modifier.height(15.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 1,
            horizontalArrangement = Arrangement.Center,
        ) {
            listOf(
                Design.Resource.artsharehubScreenshot0,
                Design.Resource.artsharehubScreenshot1,
                Design.Resource.artsharehubScreenshot2,
                Design.Resource.artsharehubScreenshot3,
                Design.Resource.artsharehubScreenshot4,
                Design.Resource.artsharehubScreenshot5,
                Design.Resource.artsharehubScreenshot6,
                Design.Resource.artsharehubScreenshot7,
            ).forEach { painter ->
                Image(
                    modifier = Modifier.fillMaxWidth(1f),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Text(
            text = buildAnnotatedString {
                append(
                    """
                        Kotlin Multiplatform(KMP), Compose Multiplatform(Web), Android(Compose), Firebase(Auth/Firestore/Functions/Storage/Analytics), Google Sign-In, Naver Map JS/Geocoding
                        
                        - 멀티모듈 구조: app(KMP Web UI), adminapp(Android 관리자 앱), data(공유 모델/Firebase 래퍼), functions(Firebase Cloud Functions)
                        - data 모듈에서 Firebase 초기화 및 인증 상태 Flow 관리, Firestore 실시간 구독으로 도메인 데이터 동기화
                        - Cloud Functions 호출 래퍼 구성 및 서버 기능 구현: 공연 등록/수정/삭제, 결제 정보 upsert, 포인트 지급, 주소 지오코딩, 사용자 생성/삭제 트리거
                        - Naver Map JS SDK 연동: 공연 위치 마커 표시, 포커스 이동, 리스트-지도 연동 모달 구현
                        - 화면 크기와 디바이스에 상관없이 일관된 UI 경험을 제공하도록 반응형 구성
                    """.trimIndent()
                )
            },
            style = Design.Text.baseStyle.copy(
                fontSize = 14.sp,
                color = Design.Color.black,
                lineHeight = 24.sp,
            ),
        )
    }
}
