package org.care.packie.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import org.care.packie.R

val NotoSansKrBlack = FontFamily(Font(R.font.notosanskr_black, FontWeight.Black))
val NotoSansKrBold = FontFamily(Font(R.font.notosanskr_bold, FontWeight.Bold))
val NotoSansKrLight = FontFamily(Font(R.font.notosanskr_light, FontWeight.Light))
val NotoSansKrMedium = FontFamily(Font(R.font.notosanskr_medium, FontWeight.Medium))
val NotoSansKrRegular = FontFamily(Font(R.font.notosanskr_regular, FontWeight.Normal))
val NotoSansKrThin = FontFamily(Font(R.font.notosanskr_thin, FontWeight.Thin))

fun packieTypography() = PackieTypography(
    title = TextStyle(
        fontFamily = NotoSansKrMedium,
        fontSize = 20.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Normal
    ),
    subTitle = TextStyle(
        fontFamily = NotoSansKrMedium,
        fontSize = 16.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Normal
    ),
    content = TextStyle(
        fontFamily = NotoSansKrMedium,
        fontSize = 14.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Normal
    ),
    body = TextStyle(
        fontFamily = NotoSansKrMedium,
        fontSize = 12.sp,
        platformStyle = PlatformTextStyle(includeFontPadding = false),
        fontWeight = FontWeight.Normal
    )
)

@Immutable
data class PackieTypography internal constructor(
    val title: TextStyle,
    val subTitle: TextStyle,
    val content: TextStyle,
    val body: TextStyle
)