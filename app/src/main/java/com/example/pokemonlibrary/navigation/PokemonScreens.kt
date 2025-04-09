package com.example.pokemonlibrary.navigation

enum class PokemonScreens{
    HOME_SCREEN;
    companion object{
        fun fromRoute(route: String?){
            when(route?.substringBefore("/")){
                HOME_SCREEN.name -> HOME_SCREEN
                null -> HOME_SCREEN
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}