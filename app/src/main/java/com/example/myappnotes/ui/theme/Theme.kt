package com.example.myappnotes.ui.theme


import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val NewColorPalette = darkColors(
    primary = Color.Black,
    secondary= Color.White,
    background = Color.LightGray,
    onBackground = Color.Black,
    surface = DarkGreenBlue,
    onSurface = Color.Black
)


@Composable
fun MyAppNotesTheme(darkTheme: Boolean = true, content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = NewColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}