package com.example.pokemonlibrary.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_table ORDER BY RANDOM() LIMIT 1")
    fun getRandomPokemon(): PokemonEntity

    @Query("SELECT * FROM pokemon_table WHERE name = :name")
    fun getPokemonByName(name: String): PokemonEntity

    @Query("SELECT * FROM pokemon_table LIMIT 16 OFFSET :offset")
    fun getPokemons(offset: Int): List<PokemonEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT COUNT(*) FROM pokemon_table")
    fun getPokemonCount(): Int

}
