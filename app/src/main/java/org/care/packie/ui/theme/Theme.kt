package org.care.packie.ui.theme

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import org.care.packie.R

private val LocalColors = staticCompositionLocalOf {
    packieDefaultColorScheme()
}

private val LocalTypography = staticCompositionLocalOf {
    packieTypography()
}

object PackieDesignSystem {
    val colors: PackieColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: PackieTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

@Composable
fun PackieTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    colorScheme: PackieColorScheme = PackieDesignSystem.colors,
    typography: PackieTypography = PackieDesignSystem.typography,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.statusBarBlue.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalColors provides colorScheme,
        LocalTypography provides typography
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = with(Modifier) {
                    fillMaxSize()
                        .paint(
                            painter = painterResource(id = R.drawable.background),
                            contentScale = ContentScale.FillBounds
                        )
                }
            ) {
                content()
            }
        }
    }
}