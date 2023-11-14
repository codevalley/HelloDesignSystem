package me.nyn.hds

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.nyn.hds.design.component.NiaBackground
import me.nyn.hds.design.component.NiaButton
import me.nyn.hds.design.component.NiaFilterChip
import me.nyn.hds.design.component.NiaGradientBackground
import me.nyn.hds.design.component.NiaLoadingWheel
import me.nyn.hds.design.theme.GradientColors
import me.nyn.hds.design.theme.LocalGradientColors
import me.nyn.hds.design.theme.NiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var isDarkTheme by remember { mutableStateOf(false) }
    NiaTheme(darkTheme = isDarkTheme) {
        MainScreen(onThemeChange = { isDarkTheme = !isDarkTheme })// Your UI components go here
    }
}

@Composable
fun MainScreen(onThemeChange: () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        BodyContent(onThemeChange, Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(onThemeChange: () -> Unit, modifier: Modifier = Modifier) {
    var gradient by remember { mutableStateOf(true) }
    NiaBackground {
        NiaGradientBackground(
            gradientColors = if (gradient) {
                LocalGradientColors.current
            } else {
                GradientColors()
            },
        ) {
            Column(modifier = modifier.padding(16.dp)) {
                NiaButton(onClick = onThemeChange) {
                    Text("Toggle Theme")
                }
                Spacer(Modifier.height(8.dp))
                NiaFilterChip(
                    label = { Text("Gradient")},
                    selected = gradient,
                    onSelectedChange = { gradient = it }
                )
                Spacer(Modifier.height(8.dp))
                NiaLoadingWheel(contentDesc = "loading")
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Hello") },
            selected = false,
            onClick = { /* Handle click */ }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("World") },
            selected = false,
            onClick = { /* Handle click */ }
        )
    }
}
