package com.example.pokemonlibrary.utils

fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}