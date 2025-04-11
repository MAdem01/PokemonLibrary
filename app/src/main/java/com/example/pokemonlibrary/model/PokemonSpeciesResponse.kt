package com.example.pokemonlibrary.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChain,
    val color: Color
)

data class EvolutionChain(
    val url: String
)

data class Color(
    val name: String
)