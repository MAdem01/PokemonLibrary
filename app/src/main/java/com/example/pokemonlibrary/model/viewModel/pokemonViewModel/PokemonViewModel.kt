package com.example.pokemonlibrary.model.viewModel.pokemonViewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.model.room.PokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _pokemon = MutableStateFlow<PokemonEntity?>(null)
    val pokemon: StateFlow<PokemonEntity?> get() = _pokemon

    private val _pokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val pokemons: StateFlow<List<PokemonEntity>> get() = _pokemons

    private val _page = mutableIntStateOf(1)
    val page: State<Int> get() = _page

    private val _offset = mutableIntStateOf(0)
    val offset: State<Int> get() = _offset

    init {
        loadRandomPokemon()
        loadPokemons(_offset.intValue)
    }

    fun loadRandomPokemon() {
        viewModelScope.launch {
            _pokemon.value = withContext(Dispatchers.IO) {
                repository.getRandomPokemon()
            }
        }
    }

    fun loadPokemons(offset: Int = _offset.intValue) {
        viewModelScope.launch {
            _pokemons.value = withContext(Dispatchers.IO) {
                repository.getPokemons(offset)
            }
        }
    }

    fun loadNextPage() {
        _page.intValue++
        _offset.intValue += 16
        loadPokemons(_offset.intValue)
    }

    fun loadPreviousPage() {
        if (_page.intValue > 1) {
            _page.intValue--
            _offset.intValue -= 16
            loadPokemons(_offset.intValue)
        }
    }
}
