package com.denicks21.darkmode.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorPalette = darkColors(
    primary = DarkPrimary,
//    primaryVariant = DarkPrimary,
    secondary = DarkSecondary,
//    secondaryVariant = DarkSecondary,
    background = DarkSecondary,
    surface = DarkPrimary,
//    error = DarkPrimary,
//    onPrimary = DarkPrimary,
//    onSecondary = DarkPrimary,
//    onBackground = DarkPrimary,
//    onSurface = DarkPrimary,
//    onError = DarkPrimary
)

private val LightColorPalette = lightColors(
    primary = LightPrimary,
//    primaryVariant = LightPrimary,
    secondary = LightSecondary,
//    secondaryVariant = LightSecondary,
    background = LightSecondary,
    surface = LightPrimary,
//    error = LightPrimary,
//    onPrimary = LightPrimary,
//    onSecondary = LightPrimary,
//    onBackground = LightPrimary,
//    onSurface = LightPrimary,
//    onError = LightPrimary
)

@Composable
fun DarkModeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.secondary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}