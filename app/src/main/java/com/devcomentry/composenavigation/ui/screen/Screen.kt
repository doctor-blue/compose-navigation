package com.devcomentry.composenavigation.ui.screen

import com.devcomentry.lib.ComposeScreen

sealed class Screen(_route: String) : ComposeScreen(_route) {
    object MainScreen : Screen("main_screen")
    object Screen1 : Screen("screen1")
    object Screen2 : Screen("screen2")
}
