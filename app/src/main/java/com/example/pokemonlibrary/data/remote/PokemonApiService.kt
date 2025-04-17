package com.example.pokemonlibrary.data.remote

import com.example.pokemonlibrary.model.PokemonEvolutionChainResponse
import com.example.pokemonlibrary.model.PokemonListResponse
import com.example.pokemonlibrary.model.PokemonResponse
import com.example.pokemonlibrary.model.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService{

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonResponse

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): PokemonSpeciesResponse

    @GET("evolution-chain/{id}")
    suspend fun getPokemonEvolutionChain(@Path("id") id: Int): PokemonEvolutionChainResponse
}