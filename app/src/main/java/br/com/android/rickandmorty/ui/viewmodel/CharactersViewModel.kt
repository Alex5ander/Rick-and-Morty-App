package br.com.android.rickandmorty.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.android.rickandmorty.repositories.RickAndMortyRepository
import br.com.android.rickandmorty.retrofit.RickAndMortyRetrofit
import br.com.android.rickandmorty.ui.uistate.MainUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class CharactersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState
    private val repository = RickAndMortyRepository(RickAndMortyRetrofit.service)

    fun setTypedName(name: String) {
        _uiState.update { currentState -> currentState.copy(typedName = name) }
    }

    fun getByName() {
        _uiState.update { currentState -> currentState.copy(page = 1, next = true, name = currentState.typedName) }
        getCharacters()
    }

    fun getCharacters() {
        val next = _uiState.value.next
        val loading = _uiState.value.loading
        if (next && !loading) {
            viewModelScope.launch {
                try {
                    _uiState.update { currentState ->
                        currentState.copy(
                            loading = true,
                            error = false
                        )
                    }

                    val name = _uiState.value.name
                    val page = _uiState.value.page
                    val response = repository.getCharacters(name = name, page = page)
                    val characters = response.results

                    _uiState.update { currentState ->
                        currentState.copy(
                            characters = if (page > 1) currentState.characters + characters else characters,
                            page = page + 1,
                            next = response.info.next != null
                        )
                    }
                } catch (e: HttpException) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            characters = emptyList(),
                            page = 0,
                            error = true
                        )
                    }
                } finally {
                    _uiState.update { currentState -> currentState.copy(loading = false) }
                }
            }
        }
    }

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            try {
                _uiState.update { currentState -> currentState.copy(loading = true, error = false) }
                val character = repository.getCharacter(id)
                _uiState.update { currentState ->
                    currentState.copy(
                        character = character,
                        loading = false,
                        error = false
                    )
                }
            } catch (_: HttpException) {
                _uiState.update { currentState -> currentState.copy(error = true) }
            } finally {
                _uiState.update { currentState -> currentState.copy(loading = false) }
            }
        }
    }
}