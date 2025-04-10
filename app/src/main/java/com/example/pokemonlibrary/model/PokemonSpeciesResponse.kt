package com.example.pokemonlibrary.model

data class PokemonSpeciesResponse(
    val evolutionChain: EvolutionChain
)

data class EvolutionChain(
    val url: String
)