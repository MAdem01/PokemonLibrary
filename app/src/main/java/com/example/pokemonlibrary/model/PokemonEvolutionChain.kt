package com.example.pokemonlibrary.model

import com.google.gson.annotations.SerializedName

data class PokemonEvolutionChainResponse(
    val evolutionChain: EvolutionData
)

data class EvolutionData(
    val species: Species,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo>
)

data class EvolvesTo(
    val species: Species,
    @SerializedName("evolves_to")
    val evolvesTo: List<EvolvesTo> = listOf()
)

