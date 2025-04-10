package com.example.pokemonlibrary.data.repository

import com.example.pokemonlibrary.data.remote.PokemonApiService
import com.example.pokemonlibrary.model.PokemonEvolutionChainResponse
import com.example.pokemonlibrary.model.PokemonResponse
import com.example.pokemonlibrary.model.PokemonSpeciesResponse

class PokemonRepository(private val apiService: PokemonApiService) {

    suspend fun getPokemon(id: Int): PokemonResponse{
       return apiService.getPokemon(id)
    }

    suspend fun getPokemonSpecies(url: String): PokemonSpeciesResponse{
        return apiService.getPokemonSpecies(extractIdFromUrl(url))
    }

    suspend fun getPokemonEvolutionChain(url: String): PokemonEvolutionChainResponse{
        return apiService.getPokemonEvolutionChain(extractIdFromUrl(url))
    }

    private fun extractIdFromUrl(url: String): Int {
        val parts = url.split("/")
        return parts[parts.size - 2].toInt()
    }
}