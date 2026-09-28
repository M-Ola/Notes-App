package com.ola.notesapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


import androidx.compose.material3.*
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color

/**
 * Light and dark color schemes for the app. simple color palette.
 */
private val LightColors = lightColorScheme(
    primary = Color(0xFF4F46E5),
    secondary = Color(0xFF6366F1),
    tertiary = Color(0xFFEC4899)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF818CF8),
    secondary = Color(0xFFA5B4FC),
    tertiary = Color(0xFFF472B6)
)

@Composable
fun NotesAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        shapes = Shapes(),
        content = content
    )
}
