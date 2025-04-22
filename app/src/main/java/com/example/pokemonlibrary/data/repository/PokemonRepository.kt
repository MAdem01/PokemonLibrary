package com.example.pokemonlibrary.data.repository

import com.example.pokemonlibrary.model.room.PokemonDao
import com.example.pokemonlibrary.model.room.PokemonEntity

class PokemonRepository(private val dao: PokemonDao) {

    fun getPokemonByName(name: String): PokemonEntity {
        return dao.getPokemonByName(name)
    }

    fun getPokemons(offset: Int): List<PokemonEntity> {
        return dao.getPokemons(offset)
    }

    fun insertAll(pokemons: List<PokemonEntity>) {
        dao.insertAll(pokemons)
    }

    fun getRandomPokemon(): PokemonEntity {
       return dao.getRandomPokemon()
    }

    fun getPokemonCount(): Int = dao.getPokemonCount()
}
