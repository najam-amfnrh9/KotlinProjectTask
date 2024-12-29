package theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

private val DarkColorPalette = darkColorScheme(
    primary = colorPrimary,
    background = colorBlack,
    onPrimary = colorBlackHover
)

private val LightColorPalette = lightColorScheme(
    primary = colorPrimary,
    background = colorWhite,
    onPrimary = colorWhiteHover
)

@Composable
fun KotlinProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colors.background)
            ) {
                content()
            }
        }
    )
}