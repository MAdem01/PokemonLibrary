package com.example.pokemonlibrary.model.viewModel.pokemonViewModel

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

    init {
        loadRandomPokemon()
        loadPokemons(0)
    }


    fun loadRandomPokemon() {
        viewModelScope.launch {
            _pokemon.value = withContext(Dispatchers.IO) {
                repository.getRandomPokemon()
            }
        }
    }

    fun loadPokemons(offset: Int) {
        viewModelScope.launch {
            _pokemons.value = withContext(Dispatchers.IO) {
                repository.getPokemons(offset)
            }
        }
    }
}
