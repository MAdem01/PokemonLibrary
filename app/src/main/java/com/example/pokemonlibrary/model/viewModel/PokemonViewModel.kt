package com.example.pokemonlibrary.model.viewModel

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.model.PokemonEvolutionChainResponse
import com.example.pokemonlibrary.model.PokemonResponse
import com.example.pokemonlibrary.model.PokemonSpeciesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random


class PokemonViewModel(private val pokemonRepository: PokemonRepository): ViewModel(){
    private val _pokemonData: MutableStateFlow<PokemonResponse?> = MutableStateFlow(null)
    val pokemonData: StateFlow<PokemonResponse?> get() = _pokemonData

    private val _pokemonSpeciesData: MutableStateFlow<PokemonSpeciesResponse?> = MutableStateFlow(null)
    val pokemonSpeciesData: StateFlow<PokemonSpeciesResponse?> get() = _pokemonSpeciesData

    private val _pokemonEvolutionChainData: MutableStateFlow<PokemonEvolutionChainResponse?> = MutableStateFlow(null)
    val pokemonEvolutionChainData: StateFlow<PokemonEvolutionChainResponse?> get() = _pokemonEvolutionChainData

    init {
        fetchPokemonData(Random.nextInt(1, 1302))
    }

    fun fetchPokemonData(id: Int){
        viewModelScope.launch {
            try{
                val pokemonData = pokemonRepository.getPokemon(id)
                _pokemonData.value = pokemonData

                pokemonData?.species?.url?.let { speciesUrl ->
                    val pokemonSpeciesData = pokemonRepository.getPokemonSpecies(speciesUrl)
                    _pokemonSpeciesData.value = pokemonSpeciesData

                    pokemonSpeciesData.evolutionChain?.url?.let { evolutionChainUrl ->
                        val pokemonEvolutionChainData = pokemonRepository.getPokemonEvolutionChain(evolutionChainUrl)
                        _pokemonEvolutionChainData.value = pokemonEvolutionChainData
                    }
                }


            }catch (e: Exception){
                throw Exception("Error fetching the data: $e")
            }
        }
    }
}