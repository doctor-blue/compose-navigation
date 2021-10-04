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
import com.devcomentry.lib.from

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
                            it.arguments?.let {  bundle->
                                val argument = Screen2Argument().from(bundle)
                                Screen2(argument)
                            }
                        }

                    }
                }
            }
        }
    }
}