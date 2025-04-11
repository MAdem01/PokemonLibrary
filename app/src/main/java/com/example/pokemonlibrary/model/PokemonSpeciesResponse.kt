package com.example.pokemonlibrary.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChain
)

data class EvolutionChain(
    val url: String
)