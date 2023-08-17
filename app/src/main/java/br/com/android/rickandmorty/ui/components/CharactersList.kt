package br.com.android.rickandmorty.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.android.rickandmorty.retrofit.Character
import br.com.android.rickandmorty.ui.navigation.navigateToCharacter

@Composable
fun CharactersList(
    characters: List<Character>,
    navController: NavHostController,
    page: Int,
    onScrollEnd: () -> Unit
) {
    val listState: LazyGridState = rememberLazyGridState()
    LaunchedEffect(page) {
        if (page == 1) {
            listState.scrollToItem(0)
        }
    }
    LaunchedEffect(listState.canScrollForward) {
        if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == characters.lastIndex) {
            onScrollEnd()
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(characters) { _, character ->
            CharacterCard(character) {
                navController.navigateToCharacter(character.id)
            }
        }
    }
}