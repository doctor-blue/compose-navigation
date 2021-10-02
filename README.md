# Compose Navigation
[![](https://jitpack.io/v/doctor-blue/compose-navigation.svg)](https://jitpack.io/#doctor-blue/compose-navigation)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)

This library will make it easier to pass arguments between screens in Jetpack Compose


## Setup

```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
	implementation 'com.github.doctor-blue:compose-navigation:1.0.0'
}
```

## How to use?
### Step 1: Create your screen class and extend it with ComposeScreen class
- Example: I have 3 screens (MainScreen, Screen1, Screen2). As you can see the _route variable is my screen name

```kotlin
mport com.devcomentry.lib.ComposeScreen

sealed class Screen(_route: String) : ComposeScreen(_route) {
    object MainScreen : Screen("main_screen")
    object Screen1 : Screen("screen1")
    object Screen2 : Screen("screen2")
}
```
### Step 2: Create a class and implement it with Argument interface. Variables in this class is your argument you wanna pass to other screen.
- Example: I want to pass a number from MainScreen to Screen2. More variables means more arguments
```kotlin
package com.devcomentry.composenavigation.ui.screen

import com.devcomentry.lib.Argument

data class Screen2Argument(val number: Int = -1) : Argument
```
### Step 3: Setup your navigation
- Same as using navigation component, but at the composable function you can give  ComposeScreen and Argument object, let me help you with the rest.
```kotlin
package com.devcomentry.composenavigation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devcomentry.composenavigation.ui.screen.*
import com.devcomentry.lib.composable
import com.devcomentry.lib.getParamFromArg

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Surface {
                Surface(
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MainScreen.route
                    ) {

                        composable(Screen.MainScreen) {
                            MainScreen(navController = navController)
                        }

                        composable(Screen.Screen1) {
                            Screen1()
                        }

                        composable(Screen.Screen2, Screen2Argument()) {
                            // get data from arguments
                            Screen2(
                                it.arguments?.getParamFromArg(Screen2Argument()) as Screen2Argument
                            )
                        }

                    }
                }
            }
        }
    }
}
```
### Note
- You can get Argument object from arguments (Instance of Bundle) like example above. Or get data from SavedStateHandle like this

```kotlin
    (savedStateHandle.get(Screen2Argument()) as Screen2Argument).let { arg ->
        
    }
```
## Want more an example?
- See [Note app](https://github.com/doctor-blue/clean-architecture-jetpack-compose-note-app-android)



## Donate
<a href="https://www.buymeacoffee.com/doctorblue" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/default-orange.png" alt="Buy Me A Coffee" height="41" width="174"></a>
[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.me/doctorblue00)