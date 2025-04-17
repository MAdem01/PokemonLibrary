package com.example.pokemonlibrary.model.viewModel.pokemonViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.model.EvolutionData
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

    private val _pokemonEvolutionChainImages: MutableStateFlow<List<String>?> = MutableStateFlow(null)
    val pokemonEvolutionChainImages: StateFlow<List<String>?> get() = _pokemonEvolutionChainImages


    init {
        fetchPokemonData(Random.nextInt(1, 1025))
    }

    fun fetchPokemonData(id: Int){
        viewModelScope.launch {
            try{
                val pokemonData = fetchPokemon(id)
                pokemonData.species.url.let { speciesUrl ->
                    val pokemonSpeciesData = fetchPokemonSpeciesData(speciesUrl)

                    pokemonSpeciesData.evolutionChain.url.let { evolutionChainUrl ->
                        fetchPokemonEvolutionData(evolutionChainUrl)
                    }
                }

            }catch (e: Exception){
                throw Exception("Error fetching the data: $e")
            }
        }
    }

    private suspend fun fetchPokemon(id: Int): PokemonResponse{
        val pokemonData = pokemonRepository.getPokemonById(id)
        _pokemonData.value = pokemonData
        return pokemonData
    }

    private suspend fun fetchPokemonSpeciesData(url: String): PokemonSpeciesResponse{
        val pokemonSpeciesData = pokemonRepository.getPokemonSpecies(url)
        _pokemonSpeciesData.value = pokemonSpeciesData
        return pokemonSpeciesData
    }

    private suspend fun fetchPokemonEvolutionData(url: String){
        val pokemonEvolutionData = pokemonRepository.getPokemonEvolutionChain(url)
        _pokemonEvolutionChainData.value = pokemonEvolutionData
        fetchPokemonEvolutionImages(pokemonEvolutionData)
    }

    private suspend fun fetchPokemonEvolutionImages(pokemonEvolutionChainData: PokemonEvolutionChainResponse){
        _pokemonEvolutionChainImages.value =
            getAllPokemonNames(pokemonEvolutionChainData.evolutionChain).map { name ->
                val pokemon = pokemonRepository.getPokemonByName(name)
                pokemon.sprites.other.officialArtwork.frontShiny
            }
    }

    private fun getAllPokemonNames(evolutionData: EvolutionData?): List<String> {
        val pokemonNames = mutableListOf<String>()
        if (evolutionData?.species == null) return pokemonNames

        evolutionData.species.name.let { pokemonNames.add(it) }

        evolutionData.evolvesTo.forEach { evolve ->
            pokemonNames.addAll(getAllPokemonNames(EvolutionData(evolve.species, evolve.evolvesTo)))
        }

        return pokemonNames
    }
}

