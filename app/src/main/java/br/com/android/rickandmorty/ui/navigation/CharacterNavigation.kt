package br.com.android.rickandmorty.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.android.rickandmorty.ui.screens.CharacterScreen
import br.com.android.rickandmorty.ui.viewmodel.CharactersViewModel


private const val characterRoute = "character/{id}"

fun NavGraphBuilder.characterScreen(navController: NavController) {
    composable(characterRoute) { backStackEntry ->
        val viewModel = viewModel<CharactersViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        backStackEntry.arguments?.getString("id")?.let { id ->
            LaunchedEffect(Unit) {
                viewModel.getCharacter(id.toInt())
            }
            CharacterScreen(uiState.character) {
                navController.popBackStack()
            }
        }
    }
}

fun NavController.navigateToCharacter(id: Int) {
    navigate(characterRoute.replace(oldValue = "{id}", newValue = id.toString()))
}