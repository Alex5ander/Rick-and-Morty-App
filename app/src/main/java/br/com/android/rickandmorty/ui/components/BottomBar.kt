package br.com.android.rickandmorty.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.android.rickandmorty.ui.navigation.charactersRoute
import br.com.android.rickandmorty.ui.navigation.navigateToCharacters
import br.com.android.rickandmorty.ui.navigation.navigateToSearchCharacters
import br.com.android.rickandmorty.ui.navigation.searchRoute

sealed class Item(val icon: ImageVector, val route: String, val onClick: () -> Unit) {
    class Home(navController: NavController) :
        Item(Icons.Default.Home, charactersRoute, navController::navigateToCharacters)

    class Search(navController: NavController) :
        Item(Icons.Default.Search, searchRoute, navController::navigateToSearchCharacters)
}

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Item.Home(navController),
        Item.Search(navController)
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, " ", Modifier.size(48.dp)) },
                selected = item.route == currentRoute,
                onClick = item.onClick
            )
        }
    }
}