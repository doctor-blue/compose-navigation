package com.devcomentry.composenavigation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun MainScreen(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            OutlinedButton(
                onClick = {
                    navController.navigate(Screen.Screen1.route)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text("Navigate to Screen1")
            }

            OutlinedButton(
                onClick = {
                    navController.navigate(
                        Screen.Screen2.setParam(
                            Screen2Argument(
                                Random.nextInt(
                                    0,
                                    50
                                )
                            )
                        )
                    )
                },
                Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp),
            ) {
                Text("Send random number to Screen2")
            }
        }
    }
}