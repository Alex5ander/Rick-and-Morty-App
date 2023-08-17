package br.com.android.rickandmorty.ui.uistate

import br.com.android.rickandmorty.retrofit.Character

data class MainUIState (
    var loading: Boolean = false,
    var typedName: String = "",
    var name: String = "",
    var page: Int = 1,
    var characters: List<Character> = listOf(),
    val character: Character? = null,
    val next: Boolean = true,
    val error: Boolean = false
)