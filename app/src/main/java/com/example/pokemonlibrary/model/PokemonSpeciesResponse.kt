package com.example.pokemonlibrary.model

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChain,
    val color: Color,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>
)

data class EvolutionChain(
    val url: String
)

data class Color(
    val name: String
)

data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String,
    val language: Language
)

data class Language(
    val name: String
)