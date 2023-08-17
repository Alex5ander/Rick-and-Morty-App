package br.com.android.rickandmorty.repositories

import br.com.android.rickandmorty.retrofit.Character
import br.com.android.rickandmorty.retrofit.CharactersResponse
import br.com.android.rickandmorty.retrofit.RickAndMortyService

class RickAndMortyRepository(
    private val service: RickAndMortyService
) {
    suspend fun getCharacters(name: String = "", page: Int = 0): CharactersResponse =
        service.getCharacters(name = name, page = page)
    suspend fun getCharacter(id: Int): Character =
        service.getCharacter(id = id)
}