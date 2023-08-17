package br.com.android.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.android.rickandmorty.ui.components.BottomBar
import br.com.android.rickandmorty.ui.components.TopBar
import br.com.android.rickandmorty.ui.navigation.characterScreen
import br.com.android.rickandmorty.ui.navigation.charactersRoute
import br.com.android.rickandmorty.ui.navigation.homeGraph
import br.com.android.rickandmorty.ui.navigation.homeGraphRoute
import br.com.android.rickandmorty.ui.navigation.searchRoute
import br.com.android.rickandmorty.ui.theme.RickAndMortyTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RickAndMortyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    println(navController.currentBackStack.collectAsState().value.map { it.destination.route })
                    val title = when(backStackEntry?.destination?.route) {
                        charactersRoute -> "Characters"
                        searchRoute -> "Search Characters"
                        else -> ""
                    }

                    val showBottomBar = when (backStackEntry?.destination?.route) {
                        charactersRoute, searchRoute -> true
                        else -> false
                    }
                    Scaffold(
                        topBar = { if(showBottomBar) { TopBar(title) } },
                        bottomBar = {
                            if (showBottomBar) {
                                BottomBar(navController)
                            }
                        }
                    ) { paddingValues ->
                        NavHost(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController,
                            startDestination = homeGraphRoute
                        ) {
                            homeGraph(navController)
                            characterScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

