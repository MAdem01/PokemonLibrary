package com.example.pokemonlibrary.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val forms: List<Form>,
    val height: Int,
    val stats: List<PokemonStat>,
    val sprites: Sprites,
    val species: Species
)

data class Form(
    val name: String
)

data class Sprites(
    val other: OtherSprites
)

data class OtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String,
    @SerializedName("front_shiny")
    val frontShiny: String
)

data class PokemonStat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatDetail
)

data class StatDetail(
    val name: String,
    val url: String
)

data class Species(
    val name: String,
    val url: String
)