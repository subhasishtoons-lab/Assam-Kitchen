package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AssamLightColorScheme = lightColorScheme(
  primary = ForestPrimary,
  onPrimary = Color.White,
  primaryContainer = ForestPrimaryContainer,
  onPrimaryContainer = ForestOnPrimaryContainer,
  inversePrimary = ForestPrimaryFixedDim,

  secondary = MustardSecondary,
  onSecondary = Color.White,
  secondaryContainer = MustardSecondaryContainer,
  onSecondaryContainer = MustardOnSecondaryContainer,

  tertiary = TerracottaTertiary,
  onTertiary = Color.White,
  tertiaryContainer = TerracottaTertiaryContainer,
  onTertiaryContainer = TerracottaOnTertiaryContainer,

  background = RicePaperBackground,
  onBackground = OnSurfaceCharcoal,
  surface = RicePaperSurface,
  onSurface = OnSurfaceCharcoal,
  surfaceVariant = SurfaceContainerHighest,
  onSurfaceVariant = OnSurfaceVariantMuted,

  surfaceContainerLowest = SurfaceContainerLowest,
  surfaceContainerLow = SurfaceContainerLow,
  surfaceContainer = SurfaceContainer,
  surfaceContainerHigh = SurfaceContainerHigh,
  surfaceContainerHighest = SurfaceContainerHighest,

  outline = OutlineGrey,
  outlineVariant = OutlineVariantLight,
  inverseSurface = InverseSurfaceDark,
  inverseOnSurface = InverseOnSurfaceLight
)

private val AssamDarkColorScheme = darkColorScheme(
  primary = ForestPrimaryFixedDim,
  onPrimary = Color(0xFF00210E),
  primaryContainer = ForestPrimaryContainer,
  onPrimaryContainer = ForestPrimaryFixed,

  secondary = MustardSecondaryFixedDim,
  onSecondary = Color(0xFF301400),
  secondaryContainer = MustardOnSecondaryContainer,
  onSecondaryContainer = MustardSecondaryFixed,

  tertiary = TerracottaTertiaryFixedDim,
  onTertiary = Color(0xFF3A0A00),
  tertiaryContainer = TerracottaTertiaryContainer,
  onTertiaryContainer = TerracottaTertiaryFixed,

  background = InverseSurfaceDark,
  onBackground = InverseOnSurfaceLight,
  surface = InverseSurfaceDark,
  onSurface = InverseOnSurfaceLight,
  surfaceVariant = Color(0xFF3B3D39),
  onSurfaceVariant = Color(0xFFC1C9BF),

  surfaceContainerLowest = Color(0xFF1B1C1A),
  surfaceContainerLow = Color(0xFF242522),
  surfaceContainer = Color(0xFF2A2B28),
  surfaceContainerHigh = Color(0xFF30312E),
  surfaceContainerHighest = Color(0xFF3B3D39),

  outline = OutlineVariantLight,
  outlineVariant = OutlineGrey,
  inverseSurface = RicePaperSurface,
  inverseOnSurface = OnSurfaceCharcoal
)

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  dynamicColor: Boolean = false, // Preserve brand identity
  content: @Composable () -> Unit,
) {
  val colorScheme = if (darkTheme) AssamDarkColorScheme else AssamLightColorScheme
  MaterialTheme(
    colorScheme = colorScheme,
    typography = Typography,
    content = content
  )
}

