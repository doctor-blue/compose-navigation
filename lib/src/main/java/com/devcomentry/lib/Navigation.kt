package com.devcomentry.lib

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator

fun <T : Argument> T.from(bundle: Bundle): T {
    val fields = this.javaClass.declaredFields
    for (field in fields) {
        field.isAccessible = true
        if (field.name != "\$stable" && bundle.containsKey(field.name)) {
            field.set(this, bundle[field.name])
        }
    }
    return this
}

fun <T : Argument> T.from(state: SavedStateHandle): T {
    val fields = this.javaClass.declaredFields
    for (field in fields) {
        field.isAccessible = true
        if (field.name != "\$stable" && state.contains(field.name)) {
            field.set(this, state[field.name])
        }
    }
    return this
}

fun NavGraphBuilder.composable(
    screen: ComposeScreen,
    arguments: Argument? = null,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    var route = screen.route
    var args = emptyList<NamedNavArgument>()

    arguments?.let {
        route = screen.getRoute(arguments)
        args = screen.getNavArgs(arguments)
    }

    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            this.route = route
            args.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}