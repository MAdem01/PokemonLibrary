package com.example.pokemonlibrary.data

import com.example.pokemonlibrary.model.PokemonEvolutionChain
import com.example.pokemonlibrary.model.PokemonResponse
import com.example.pokemonlibrary.model.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService{
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonResponse

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpeciesResponse

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolutionChain(@Path("id") id: Int): PokemonEvolutionChain
}