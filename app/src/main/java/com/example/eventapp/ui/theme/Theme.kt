package com.example.eventapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Custom color values




private val RedLight = Color(0xFFEF9A9A)
private val RedDark = Color(0xFFB71C1C)

// Dark color scheme
private val DarkColorPalette = darkColorScheme(
    primary = Purple80,
    onPrimary = Color.White,
    primaryContainer = Purple40,
    onPrimaryContainer = Color.White,
    secondary = PurpleGrey80,
    onSecondary = Color.White,
    secondaryContainer = PurpleGrey40,
    onSecondaryContainer = Color.White,
    tertiary = Pink80,
    onTertiary = Color.White,
    tertiaryContainer = Pink40,
    onTertiaryContainer = Color.White,
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    error = RedDark,
    onError = Color.White,
    errorContainer = RedLight,
    onErrorContainer = Color.White
)

// Light color scheme
private val LightColorPalette = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = Purple80,
    onPrimaryContainer = Color.White,
    secondary = PurpleGrey40,
    onSecondary = Color.White,
    secondaryContainer = PurpleGrey80,
    onSecondaryContainer = Color.White,
    tertiary = Pink40,
    onTertiary = Color.White,
    tertiaryContainer = Pink80,
    onTertiaryContainer = Color.White,
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    error = RedLight,
    onError = Color.White,
    errorContainer = RedDark,
    onErrorContainer = Color.White
)

@Composable
fun EventAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Select color scheme based on theme and dynamic color support
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Ensure Typography is defined
        shapes = Shapes, // Ensure Shapes is defined
        content = content
    )
}

// Define Typography (adjust as needed for your app)
//val Typography = androidx.compose.material3.Typography()

// Define Shapes (adjust as needed for your app)
val Shapes = androidx.compose.material3.Shapes()
