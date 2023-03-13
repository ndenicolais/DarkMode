package com.denicks21.darkmode

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.denicks21.darkmode.ui.composables.Switcher
import com.denicks21.darkmode.ui.theme.DarkModeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            DarkModeTheme(darkTheme = darkTheme) {
                MainScreen(
                    theme = darkTheme,
                    themeUpdated = { darkTheme = !darkTheme }
                )
            }
        }
    }
}

@Composable
fun MainScreen(theme: Boolean, themeUpdated: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Switcher(
            darkTheme = theme,
            size = 100.dp,
            padding = 5.dp,
            onClick = themeUpdated
        )
    }
}