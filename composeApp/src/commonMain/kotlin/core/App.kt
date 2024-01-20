package core

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import screens.home_screen.HomeScreen

@Composable
fun App() {
    MaterialTheme {
        Navigator(screen = HomeScreen())
    }
}

