package com.devcomentry.lib

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.*
import androidx.navigation.compose.ComposeNavigator

fun Bundle.getParamFromArg(argument: Argument): Argument {
    val fields = argument.javaClass.declaredFields
    for (field in fields) {
        field.isAccessible = true
        if (field.name != "\$stable") {
            field.set(argument, this[field.name])
        }
    }
    return argument
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

fun SavedStateHandle.get(argument: Argument): Argument {
    val fields = argument.javaClass.declaredFields
    for (field in fields) {
        field.isAccessible = true
        if (field.name != "\$stable") {
            field.set(argument, this[field.name])
        }
    }
    return argument
}