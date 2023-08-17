package br.com.android.rickandmorty.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.android.rickandmorty.ui.screens.CharactersScreen
import br.com.android.rickandmorty.ui.viewmodel.CharactersViewModel

const val charactersRoute = "characters"

fun NavGraphBuilder.charactersScreen(
    navController: NavHostController
) {
    composable(charactersRoute) {
        val viewModel = viewModel<CharactersViewModel>()
        val uiState = viewModel.uiState.collectAsState().value
        LaunchedEffect(Unit) {
            viewModel.getCharacters()
        }
        CharactersScreen(uiState = uiState, navController = navController, viewModel::getCharacters)
    }
}

fun NavController.navigateToCharacters() {
    navigate(charactersRoute) {
        popBackStack()
    }
}