package com.example.pokemonlibrary.model

data class PokemonEvolutionChainResponse(
    val evolutionChain: EvolutionData
)

data class EvolutionData(
    val species: Species,
    val evolvesTo: List<EvolvesTo>
)

data class EvolvesTo(
    val species: Species,
    val evolvesTo: List<EvolvesTo> = listOf()
)

