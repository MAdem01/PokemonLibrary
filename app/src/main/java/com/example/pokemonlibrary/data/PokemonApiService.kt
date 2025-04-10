package com.example.pokemonlibrary.data

import com.example.pokemonlibrary.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService{
    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): PokemonResponse
}