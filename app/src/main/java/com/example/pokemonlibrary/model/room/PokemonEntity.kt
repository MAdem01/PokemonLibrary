package com.example.pokemonlibrary.model.room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pokemon_table")
@TypeConverters(Converters::class)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,

    @Embedded(prefix = "sprites_")
    val sprites: Sprites,

    @TypeConverters(Converters::class)
    val stats: List<Stat>,

    @TypeConverters(Converters::class)
    val types: List<String>,

    val color: String,

    @TypeConverters(Converters::class)
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorText>,

    @TypeConverters(Converters::class)
    @SerializedName("evolution_chain")
    val evolutionChain: List<String>
)


data class Sprites(
    val front_default: String?,
    val back_default: String?,
    val front_shiny: String?,
    val back_shiny: String?,
    val official_artwork: String?
)

data class Stat(
    val name: String,
    val base_stat: Int
)

data class FlavorText(
    val version: String,
    val text: String
)
