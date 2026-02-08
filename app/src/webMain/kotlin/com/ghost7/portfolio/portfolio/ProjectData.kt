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
                        ■ 2021년 (10월 ~ 12월)
                        
                        [접근성 개선]
                        - 일반채팅방 사이드메뉴의 알림/즐겨찾기 버튼에서 톡백 활성화 시 중복 음성 출력 제거
                        - "선택됨, 알림 켜짐, 버튼" → "알림 켜짐, 버튼"으로 간소화하여 접근성 정보 중복 제공 방지
                        
                        [웨어OS 알림 동기화]
                        - 워치(WearOS) 알림 ON/OFF 여부가 모바일 카카오톡 설정에 따라 동작하도록 수정
                        - 모바일에서 채팅방 알림이 꺼져 있으면 워치에서도 알림이 울리지 않도록 구현
                        - 전체설정 → 알림 → 소리/진동 ON/OFF에 따른 워치 알림 동작을 모바일과 동일하게 동기화
                        
                        [서브디바이스 지원]
                        - 안드로이드 크롬북에서 카카오톡 실행 시 서브디바이스 로그인 동선 구현
                        - 크롬북 환경 감지 로직 작성 및 디버깅 방법 위키 문서화
                        - 크롬북에서의 로그인 플로우 및 실행 환경 판단 로직 개발
                        
                        ■ 2022년 (1월 ~ 12월)
                        
                        [웨어OS 기능 확장]
                        - 채팅방 알림이 꺼져 있을 때 웨어러블 디바이스로 알림을 보내지 않도록 최적화
                        - 알림 최적화 기능 추가: 모바일 화면이 켜져 있을 때 워치 알림 전송 차단으로 배터리 절약
                        - 전체설정에서 무음 설정 시 진동도 울리지 않도록 수정하여 사용자 설정 일관성 확보
                        - 웨어톡 접근성 개선: 채팅 버튼의 새 메시지 정보 제공, 이모티콘/사진 메시지 대체 텍스트 추가, 보낸 사람 정보 제공, 불필요한 음성 출력 및 제스처 안내 제거
                        
                        [서브디바이스 인증 플로우]
                        - 프로필 동의 100 작업: 서버 응답 status에 따른 분기 처리 구현
                        - status:-126 수신 시 약관 동의 얼럿 노출 및 외부 브라우저로 계정 페이지 이동
                        - status:31 수신 시 이메일 인증 페이지로 이동 및 "다른 이메일로 인증" 버튼 신규 추가
                        - anotherEmailVerificationUri 필드를 활용한 외부 브라우저 연동 및 로그인 시작 페이지 이동 로직
                        
                        [홈 UI 개편]
                        - 홈 전체서비스 화면 구조 변경 및 디자인 필터링 작업
                        - 사용자 경험 개선을 위한 전체서비스 메뉴 재구성
                        
                        [코틀린 마이그레이션]
                        - com.kakao.talk.singleton 패키지 코틀린 변환
                        - com.kakao.talk.activity.setting 패키지 코틀린 변환
                        - 레거시 Java 코드를 Kotlin으로 전환하여 코드 가독성 및 유지보수성 향상
                        
                        [채팅방 UI/UX 개선]
                        - 소프트 키보드 애니메이션에 WindowInsetsAnimation 적용으로 비주얼 퀄리티 향상
                        - 키보드 올라가고 내려갈 때 부드러운 전환 효과 구현
                        
                        [안드로이드 13 대응]
                        - 안드로이드 13 동작 변경사항(모든 앱) 중 개인 정보 보호 & 보안 섹션 정리 및 위키 문서화
                        - 안드로이드 13 새로운 기능 조사, 정리 및 팀 내 공유
                        
                        [CS 지원 개선]
                        - 오류 도구의 알림 설정 보고 기능에 디바이스 정보 추가
                        - CS 처리 효율성 향상을 위한 디바이스 환경 정보 수집 및 위키 문서화
                        
                        ■ 2023년 (1월 ~ 12월)
                        
                        [프로필 펑 개발 - 핵심 담당]
                        프로필 펑 서비스의 핵심 기능인 만들기 화면과 조회 화면을 설계 및 개발. 사용자 경험과 유지보수성을 최우선으로 고려한 아키텍처 구성
                        
                        • 펑 만들기 화면
                        - 꾸미기 아이템(텍스트, 이모티콘, 스티커, 인티커, 사진, 동영상 등) 생성/추가/편집 기능 구현
                        - 프로필 편집 코드 검토 후 dependency 최소화 전략 수립: 필수 부분만 활용, 개선 가능 부분은 수정, 유사 기능은 참고
                        - 인스타그램 스토리, 텔레그램 스토리 등 타 서비스의 장점 벤치마킹 및 반영
                        - SubsamplingScaleImageView 라이브러리와 삼성 갤러리 앱 분석을 통한 고화질 이미지 지원
                        - OOM 방지를 위한 이미지 처리 최적화: 타일 기반 렌더링으로 메모리 효율성 확보
                        - 신규 크루도 쉽게 이해하고 유지보수할 수 있는 코드 구조 설계
                        
                        • 펑 뷰잉 화면 (내 펑)
                        - 조회한 친구 바텀시트 상단에 리액션 이모지 중력 애니메이션 구현
                        - 사용자 인터랙션에 따른 자연스러운 물리 효과 적용
                        
                        • 릴리즈 후 운영
                        - 신규 기능 개발 및 유지보수 지속 진행
                        - 사용자 피드백 반영 및 안정성 개선
                        
                        • 개발 회고 및 공유
                        - "if 톡안드 펑 개발 공유" 세션에서 펑 만들기 구성 및 개발 내용 발표
                        - 팀 내 기술 공유 및 노하우 전파
                        
                        [Gradle 빌드 시스템 개선]
                        - buildSrc → build-logic 마이그레이션 작업 수행
                        - Gradle 구성 및 설정 공통화로 빌드 성능 향상 및 일관성 확보
                        - AndroidLibraryConventionPlugin, JvmLibraryConventionPlugin 생성 및 프로젝트 전반에 적용
                        - 모듈별 중복 설정 제거 및 유지보수성 개선
                        
                        ■ 2024년 (1월 ~ 6월)
                        
                        [프로필 펑 고도화]
                        • Styled Text 신규 아이템 개발
                        - 재사용 가능한 독립 컴포넌트로 설계하여 펑에 먼저 적용
                        - 이후 프로필에서도 활용할 수 있도록 범용성 확보
                        - 다양한 텍스트 스타일링 옵션 제공으로 사용자 표현력 향상
                        
                        • 펑 조회 화면 리팩토링
                        - 사용자 경험 개선 및 개발 생산성 강화를 목표로 전면 리팩토링
                        - 프로필과의 의존성 최소화하면서도 필수 부분은 활용
                        - 개선 가능한 부분 수정, 유사 기능 참고, 타 서비스 장점 반영
                        - 신규 크루의 유지보수 용이성을 고려한 코드 명확화
                        
                        • 지속적 유지보수
                        - 신규 기능 개발, QA 진행 및 안정성 개선
                        - 사용자 피드백 기반 기능 개선 및 버그 수정
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
                        ■ 2024년 (6월 ~ 12월)
                        
                        [핵심 UI 컴포넌트 개발]
                        • 미디어 뷰어
                        - 사진/동영상 뷰어 구현으로 미디어 콘텐츠 몰입형 경험 제공
                        - 제스처 기반 인터랙션(줌, 스와이프) 및 미디어 제어 기능 구현
                        
                        • 유저 프로필 메인 & 편집
                        - 프로필 조회 화면 및 편집 화면 설계 및 구현
                        - 실시간 미리보기 제공으로 사용자 편의성 향상
                        
                        • 개인 메이트 프로필 메인 & 편집
                        - 메이트(반려동물) 프로필 전용 UI/UX 설계 및 구현
                        - 유저 프로필과 분리된 독립적인 컴포넌트 구조 설계
                        
                        • 초대장
                        - 그룹 초대장 생성 및 공유 기능 구현
                        - 카카오톡, SMS 등 다양한 공유 채널 연동
                        
                        • 공통 컴포넌트
                        - 친구 리스트 바텀시트: 친구 선택 UI 재사용 가능한 컴포넌트로 설계
                        - 검색창 UI: 일관된 검색 경험을 위한 표준 컴포넌트 개발
                        - 편집 텍스트 필드: 다양한 입력 필드에서 활용 가능한 범용 컴포넌트
                        
                        [개발 철학]
                        - 사용자 경험을 최우선으로 고려한 UI/UX 설계
                        - 공통 컴포넌트의 재사용성과 범용성 확보로 개발 생산성 향상
                        - 적절한 구조 분리로 유지보수성 및 확장성 고려
                        - Compose 기반 UI와 상태 관리 로직의 명확한 분리
                        - 각 컴포넌트의 독립적 동작을 위한 설계
                        - 테스트 용이성을 고려한 아키텍처 구성
                        
                        ■ 2025년 (1월 ~ 현재)
                        
                        [앱 주요 기능 개발 및 UI 고도화]
                        • 그룹탭 화면 개편
                        - 그룹탭 화면 구성 전면 변경 및 서브탭 로직 이동
                        - 코드 모듈화 및 유지보수성 개선
                        - "카나나 활용백서" 메뉴 추가: URL 연결 및 API 파싱 이슈 해결로 유저 가이드 제공
                        - PromotionBottomSheet 등 불필요 코드 제거로 코드 단순화
                        
                        • 대화 탭 화면 개편
                        - 대화 목록 리스트의 프리뷰 텍스트 정렬 개선
                        - 뱃지 카운트 로직 정확도 향상
                        - UI 디테일 완성으로 사용자 경험 개선
                        
                        • 미디어 뷰어 아키텍처 개선
                        - 기존 페이징 로직과 모델을 도메인 레이어에서 UI 레이어로 이동
                        - UseCase를 거치지 않고 ViewModel에서 직접 페이징 플로우 관리하도록 구조 최적화
                        - 비즈니스 로직을 ViewModel로 통합하고 UiState 중심 상태 관리로 단순화
                        - Common UI 적용으로 컴포넌트 표준화
                        - 동영상 재생 버튼 노출 제어 및 재시도 로직 정리로 코드 복잡도 감소
                        - 불필요한 코드 제거 및 네이밍 통일로 유지보수성 향상
                        
                        [서비스 안정성 확보 및 버그 해결]
                        • 메시지 동기화 및 뱃지 카운트 오류 해결
                        - 대화방 생성 시 푸시와 동기화 타이밍 문제로 안 읽은 메시지 수가 '2'로 잘못 표시되는 현상 분석
                        - message.extension.isWelcome 플래그를 활용한 로직 수정으로 정확한 카운트 제공
                        
                        • 접근성 및 디스플레이 호환성 개선
                        - 글자 크기를 크게 설정할 경우 최신 메시지가 잘리는 현상 수정
                        - 대화방 리스트 프리뷰에서 줄바꿈 처리 미적용 문제 해결로 가독성 향상
                        
                        • 예외 상황 방어 로직 추가
                        - 프리셋 로컬 데이터 손상 시 앱 비정상 종료 방지를 위한 복구 및 방어 로직 추가
                        - 콜드 스타트 시 발생 가능한 이슈 해결로 앱 구동 초기 경험 개선
                        
                        [아키텍처 개선 및 기술 부채 해소]
                        • ViewModel 및 로직 분리
                        - GroupViewModel, ConversationListViewModel 등 주요 뷰모델의 비대해진 로직 정리
                        - MainNav, Group, ConversationList 간 역할 명확히 분리하여 유지보수성 향상
                        
                        • 코드 최신화 및 마이그레이션 대비
                        - invitation 패키지 등 사용하지 않는 레거시 코드 제거로 프로젝트 경량화
                        - 뷰모델 초기화 시 awaitUiPreparingAndLaunch 적용 등 코루틴 활용 구조 개선
                        - 향후 마이그레이션을 고려한 코드 구조 정비
                        
                        [팀 협업 및 문제 해결]
                        - 팀원들과의 긴밀한 협업으로 문제 신속 파악 및 해결
                        - 효율적인 커뮤니케이션으로 요구사항 명확화 및 역할 조율
                        - 예상치 못한 문제 상황에서 대안 모색 및 솔루션 제안
                        - 적극적인 의견 공유로 프로젝트 원활한 진행 기여
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
                        
                        - 멀티모듈 구조: app(KMP Web UI), functions(Firebase Cloud Functions), adminapp(Android 관리자 앱), data(공유 모델/Firebase 래퍼)
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
