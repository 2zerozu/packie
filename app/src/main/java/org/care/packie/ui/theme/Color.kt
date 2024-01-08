package org.care.packie.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val BackgroundBlackAlpha50 = Color(0x80191C1B)
val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)
val ButtonWhite = Color(0xCCFFFFFF)
val GreenCheck = Color(0xFF24FF00)
val StatusBarBlue = Color(0xFF6A7AAE)

@Stable
class PackieColorScheme(
    backgroundBlackAlpha50: Color,
    black: Color,
    white: Color,
    buttonWhite: Color,
    greenCheck: Color,
    statusBarBlue: Color
) {
    var backgroundBlackAlpha50 by mutableStateOf(backgroundBlackAlpha50, structuralEqualityPolicy())
        internal set
    var black by mutableStateOf(black, structuralEqualityPolicy())
        internal set
    var white by mutableStateOf(white, structuralEqualityPolicy())
        internal set
    var buttonWhite by mutableStateOf(buttonWhite, structuralEqualityPolicy())
        internal set
    var greenCheck by mutableStateOf(greenCheck, structuralEqualityPolicy())
        internal set

    var statusBarBlue by mutableStateOf(statusBarBlue, structuralEqualityPolicy())
        internal set

    fun copy(
        backgroundBlackAlpha50: Color,
        black: Color,
        white: Color,
        buttonWhite: Color,
        greenCheck: Color,
        statusBarBlue: Color
    ) = PackieColorScheme(
        backgroundBlackAlpha50 = backgroundBlackAlpha50,
        black = black,
        white = white,
        buttonWhite = buttonWhite,
        greenCheck = greenCheck,
        statusBarBlue = statusBarBlue
    )

}

fun packieDefaultColorScheme() = PackieColorScheme(
    backgroundBlackAlpha50 = BackgroundBlackAlpha50,
    black = Black,
    white = White,
    buttonWhite = ButtonWhite,
    greenCheck = GreenCheck,
    statusBarBlue = StatusBarBlue
)
