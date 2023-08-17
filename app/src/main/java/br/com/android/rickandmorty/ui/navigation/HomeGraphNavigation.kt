package br.com.android.rickandmorty.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation

const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(
    navController: NavHostController,
) {
    navigation(
        startDestination = charactersRoute,
        route = homeGraphRoute
    ) {
        charactersScreen(navController)
        searchCharactersScreen(navController)
    }
}

fun NavController.navigateToHomeGraph() {
    navigate(homeGraphRoute)
}