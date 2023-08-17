package br.com.android.rickandmorty.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.android.rickandmorty.ui.screens.SearchCharactersScreen
import br.com.android.rickandmorty.ui.viewmodel.CharactersViewModel

const val searchRoute = "search"

fun NavGraphBuilder.searchCharactersScreen(
    navController: NavHostController,
) {
    composable(searchRoute) {
        val viewModel = viewModel<CharactersViewModel>()
        val uiState = viewModel.uiState.collectAsState().value
        SearchCharactersScreen(
            uiState = uiState,
            navController = navController,
            setName = viewModel::setTypedName,
            getByName = viewModel::getByName,
            getCharacters = viewModel::getCharacters
        )
    }
}

fun NavController.navigateToSearchCharacters() {
    navigate(searchRoute) {
        popBackStack()
    }
}