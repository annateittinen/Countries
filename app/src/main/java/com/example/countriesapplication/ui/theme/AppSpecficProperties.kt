package com.example.countriesapplication.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Immutable
data class AppColor(
    val background: Color,
    val onBackground: Color,
    var cardContainer: Color,
    var onCardContainer: Color,
    val textPrimary: Color,
    val textSecondary: Color,
)

@Immutable
data class AppTypography(
    val mainTitle: TextStyle,
    val countryNameTitle: TextStyle,
    val countryFieldValue: TextStyle,
    val countryFieldLabel: TextStyle,
)

val LocalAppColor = staticCompositionLocalOf {
    AppColor(
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        cardContainer = Color.Unspecified,
        onCardContainer = Color.Unspecified,
        textPrimary = Color.Unspecified,
        textSecondary = Color.Unspecified,
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        mainTitle = TextStyle.Default,
        countryNameTitle = TextStyle.Default,
        countryFieldValue = TextStyle.Default,
        countryFieldLabel = TextStyle.Default,
    )
}

