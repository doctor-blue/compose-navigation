package com.devcomentry.lib

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

open class ComposeScreen(val route: String){
    fun setParam(argument: Argument): String {
        var routeString = "$route?"
        val fields = argument.javaClass.declaredFields
        for (field in fields) {
            field.isAccessible = true
            if (field.name != "\$stable")
                routeString += "${field.name}=${field.get(argument)}&"
        }
        routeString = routeString.dropLast(1)
        return routeString
    }

    fun getRoute(argument: Argument): String {
        var routeString = "$route?"
        val fields = argument.javaClass.declaredFields
        for (field in fields) {
            if (field.name != "\$stable")
                routeString += "${field.name}={${field.name}}&"
        }
        routeString = routeString.dropLast(1)
        return routeString
    }

    fun getNavArgs(argument: Argument): List<NamedNavArgument> {
        val fields = argument.javaClass.declaredFields
        val args = mutableListOf<NamedNavArgument>()
        for (field in fields) {
            field.isAccessible = true
            if (field.name != "\$stable")
                args.add(
                    navArgument(field.name) {
                        type = NavType.inferFromValueType(field.get(argument))
                    }
                )
        }
        return args
    }
}
