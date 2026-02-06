package com.ghost7.portfolio.portfolio

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import portfolio.app.generated.resources.NotoSansKR_Medium
import portfolio.app.generated.resources.Res
import portfolio.app.generated.resources.logo_artsharehub
import portfolio.app.generated.resources.logo_chaintodo
import portfolio.app.generated.resources.logo_dietofhell
import portfolio.app.generated.resources.logo_growskills
import portfolio.app.generated.resources.logo_kakaotalk
import portfolio.app.generated.resources.logo_kanana
import portfolio.app.generated.resources.logo_lifetime
import portfolio.app.generated.resources.logo_wifion

object Design {

    object Color {
        val gray50 = Color(0XFFFAFAFA)
        val gray100 = Color(0XFFF5F5F5)
        val gray200 = Color(0XFFEEEEEE)
        val gray300 = Color(0XFFE0E0E0)
        val gray400 = Color(0XFFBDBDBD)
        val gray500 = Color(0XFF9E9E9E)
        val gray600 = Color(0XFF757575)
        val gray700 = Color(0XFF616161)
        val gray800 = Color(0XFF424242)
        val gray900 = Color(0XFF212121)

        val transparent = Color(0X00000000)
        val white = Color(0XFFFFFFFF)
        val black = Color(0XFF000000)

        fun androidx.compose.ui.graphics.Color.a5() = this.copy(alpha = 0.05f)
        fun androidx.compose.ui.graphics.Color.a10() = this.copy(alpha = 0.1f)
        fun androidx.compose.ui.graphics.Color.a15() = this.copy(alpha = 0.15f)
        fun androidx.compose.ui.graphics.Color.a20() = this.copy(alpha = 0.2f)
        fun androidx.compose.ui.graphics.Color.a25() = this.copy(alpha = 0.25f)
        fun androidx.compose.ui.graphics.Color.a30() = this.copy(alpha = 0.3f)
        fun androidx.compose.ui.graphics.Color.a35() = this.copy(alpha = 0.35f)
        fun androidx.compose.ui.graphics.Color.a40() = this.copy(alpha = 0.4f)
        fun androidx.compose.ui.graphics.Color.a45() = this.copy(alpha = 0.45f)
        fun androidx.compose.ui.graphics.Color.a50() = this.copy(alpha = 0.5f)
        fun androidx.compose.ui.graphics.Color.a55() = this.copy(alpha = 0.55f)
        fun androidx.compose.ui.graphics.Color.a60() = this.copy(alpha = 0.6f)
        fun androidx.compose.ui.graphics.Color.a65() = this.copy(alpha = 0.65f)
        fun androidx.compose.ui.graphics.Color.a70() = this.copy(alpha = 0.7f)
        fun androidx.compose.ui.graphics.Color.a75() = this.copy(alpha = 0.75f)
        fun androidx.compose.ui.graphics.Color.a80() = this.copy(alpha = 0.8f)
        fun androidx.compose.ui.graphics.Color.a85() = this.copy(alpha = 0.85f)
        fun androidx.compose.ui.graphics.Color.a90() = this.copy(alpha = 0.9f)
        fun androidx.compose.ui.graphics.Color.a95() = this.copy(alpha = 0.95f)
    }

    object Text {
        val baseStyle: TextStyle
            @Composable get() = TextStyle(fontFamily = FontFamily(Font(Res.font.NotoSansKR_Medium)))
    }

    object Icon {
        val logoLifetime: Painter @Composable get() = painterResource(Res.drawable.logo_lifetime)
        val logoGrowskills: Painter @Composable get() = painterResource(Res.drawable.logo_growskills)
        val logoWifion: Painter @Composable get() = painterResource(Res.drawable.logo_wifion)
        val logoDietofhell: Painter @Composable get() = painterResource(Res.drawable.logo_dietofhell)
        val logoKakaotalk: Painter @Composable get() = painterResource(Res.drawable.logo_kakaotalk)
        val logoChaintodo: Painter @Composable get() = painterResource(Res.drawable.logo_chaintodo)
        val logoKanana: Painter @Composable get() = painterResource(Res.drawable.logo_kanana)
        val logoArtsharehub: Painter @Composable get() = painterResource(Res.drawable.logo_artsharehub)
    }
}
