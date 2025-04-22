package com.example.pokemonlibrary.model.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStatList(stats: List<Stat>): String = gson.toJson(stats)

    @TypeConverter
    fun toStatList(stats: String): List<Stat> = gson.fromJson(stats, Array<Stat>::class.java).toList()

    @TypeConverter
    fun fromFlavorTextList(entries: List<FlavorText>?): String {
        return Gson().toJson(entries ?: emptyList<FlavorText>())
    }

    @TypeConverter
    fun toFlavorTextList(data: String?): List<FlavorText> {
        if (data.isNullOrEmpty()) return emptyList()
        val listType = object : TypeToken<List<FlavorText>>() {}.type
        return Gson().fromJson(data, listType)
    }


    @TypeConverter
    fun fromTypeList(types: List<String>?): String {
        return Gson().toJson(types ?: emptyList<String>())
    }

    @TypeConverter
    fun toTypeList(data: String?): List<String> {
        if (data.isNullOrEmpty()) return emptyList()
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(data, listType)
    }

}
