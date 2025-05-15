package com.example.pokemonlibrary.utils

import android.content.Context
import android.util.Log
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.model.room.PokemonDatabase
import com.example.pokemonlibrary.model.room.PokemonEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

fun getPokemonTypeIcon(iconName: String): Int{
    return when(iconName){
        "bug" -> R.drawable.bug
        "dark" -> R.drawable.dark
        "dragon" -> R.drawable.dragon
        "electric" -> R.drawable.electric
        "fairy" -> R.drawable.fairy
        "fighting" -> R.drawable.fighting
        "fire" -> R.drawable.fire
        "flying" -> R.drawable.flying
        "ghost" -> R.drawable.ghost
        "grass" -> R.drawable.grass
        "ground" -> R.drawable.ground
        "ice" -> R.drawable.ice
        "normal" -> R.drawable.normal
        "poison" -> R.drawable.poison
        "psychic" -> R.drawable.psychic
        "rock" -> R.drawable.rock
        "steel" -> R.drawable.steel
        else -> R.drawable.water
    }
}

fun getScreenColor(colorName: String): Int{
    return when(colorName){
        "green" -> R.color.green
        "red" -> R.color.red
        "blue" -> R.color.blue
        "orange" -> R.color.orange
        "yellow" -> R.color.yellow
        "brown" -> R.color.brown
        "pink" -> R.color.pink
        else -> R.color.purple
    }
}

fun preloadDatabaseIfNeeded(context: Context, database: PokemonDatabase) {
    val dao = database.pokemonDao()
    val repository = PokemonRepository(dao)

    runBlocking(Dispatchers.IO) {
        val count = repository.getPokemonCount()
        if (count == 0) {
            val jsonString = context.assets.open("pokemon_data.json")
                .bufferedReader()
                .use { it.readText() }

            val pokemonListType = object : TypeToken<List<PokemonEntity>>() {}.type
            val pokemonList: List<PokemonEntity> = Gson().fromJson(jsonString, pokemonListType)

            Log.d("pokemonn", "$pokemonList")
            repository.insertAll(pokemonList)
        }
    }
}
