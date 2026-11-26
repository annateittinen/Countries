package com.example.countriesapplication.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val lightThemeColors = AppColor(
    background = Color(0xFFFFFBFF), //opaque white
    onBackground = Color.Black,
    cardContainer = Color(red = 231, green = 224, blue = 236),
    onCardContainer = Color(red = 73, green = 69, blue = 79),
    textPrimary = Color(0xFF1C1B1F),
    textSecondary = Color(0xFF49454F),
)

private val darkThemeColors = AppColor(
    background = Color(0xFF141218), //dark shade of charcoal
    onBackground = Color.White,
    cardContainer = Color(red = 73, green = 69, blue = 79),
    onCardContainer = Color(red = 202, green = 196, blue = 208),
    textPrimary = Color(0xFFE6E1E5),
    textSecondary = Color(0xFFCAC4D0),
)

private val themeTypography = AppTypography(
    mainTitle = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Normal,
    ),
    countryNameTitle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    countryFieldValue = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
    ),
    countryFieldLabel = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
    )
)

object CountriesApplicationTheme {

    val colors: AppColor
     @Composable
     //@RequiresPermission.Read
     get() = LocalAppColor.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current

    @Composable
    operator fun invoke(
        isDarkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit,
    ) {
        val themeColors = if (isDarkTheme) darkThemeColors else lightThemeColors
        CompositionLocalProvider(
            LocalAppColor provides themeColors,
            LocalAppTypography provides themeTypography,
            content = content,
        )
    }
}


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CountriesApplicationTheme_old(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}