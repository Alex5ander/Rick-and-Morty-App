package br.com.android.rickandmorty.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.android.rickandmorty.ui.components.CharactersList
import br.com.android.rickandmorty.ui.components.EmptyListWarning
import br.com.android.rickandmorty.ui.components.SearchHeader
import br.com.android.rickandmorty.ui.uistate.MainUIState

@Composable
fun SearchCharactersScreen(
    uiState: MainUIState,
    navController: NavHostController,
    setName: (String) -> Unit,
    getByName: () -> Unit,
    getCharacters: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        SearchHeader(
            text = uiState.typedName,
            onValueChange = setName,
            onSubmit = getByName,
            searching = uiState.loading
        )
        if (uiState.error) {
            EmptyListWarning()
        } else {
            Box(Modifier.fillMaxSize()) {
                CharactersList(
                    characters = uiState.characters,
                    navController = navController,
                    page = uiState.page,
                    onScrollEnd = getCharacters
                )
                if (uiState.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center),
                        strokeWidth = 4.dp
                    )
                }
            }
        }
    }
}