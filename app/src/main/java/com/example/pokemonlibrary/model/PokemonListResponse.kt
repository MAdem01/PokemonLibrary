package com.example.pokemonlibrary.model

data class PokemonListResponse(
    val next: String,
    val previous: String,
    val results: List<Result>
)

data class Result(
    val name: String
)