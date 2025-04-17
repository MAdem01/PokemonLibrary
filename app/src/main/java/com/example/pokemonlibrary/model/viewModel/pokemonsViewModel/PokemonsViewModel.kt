package com.example.pokemonlibrary.model.viewModel.pokemonsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.model.PokemonListResponse
import com.example.pokemonlibrary.model.PokemonResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonsViewModel(private val pokemonRepository: PokemonRepository): ViewModel(){
    private val _pokemonNames: MutableStateFlow<PokemonListResponse?> = MutableStateFlow(null)
    val pokemonNames: StateFlow<PokemonListResponse?> get() = _pokemonNames

    private val _pokemonsData: MutableStateFlow<List<PokemonResponse>?> = MutableStateFlow(null)
    val pokemonsData: StateFlow<List<PokemonResponse>?> get() = _pokemonsData

    init{
        fetchPokemons(limit = 16, offset = 0)
    }

    fun fetchPokemons(limit: Int, offset: Int){
        viewModelScope.launch {
            try {
                val pokemonNames = pokemonRepository.getPokemons(limit = limit, offset = offset)
                _pokemonNames.value = pokemonNames

                pokemonNames.let {
                    val pokemonsData = pokemonNames.results.map{ result ->
                        pokemonRepository.getPokemonByName(result.name)
                    }
                    _pokemonsData.value = pokemonsData
                }
            }catch (e: Exception){
                throw Exception("Error fetching the data: $e")
            }
        }
    }
}