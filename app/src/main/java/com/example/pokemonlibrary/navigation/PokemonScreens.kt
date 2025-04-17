package com.example.pokemonlibrary.navigation

enum class PokemonScreens{
    HOME_SCREEN,
    ABOUT_SCREEN,
    POKEMON_SCREEN,
    FAVOURITES_SCREEN;
    companion object{
        fun fromRoute(route: String?){
            when(route?.substringBefore("/")){
                HOME_SCREEN.name -> HOME_SCREEN
                ABOUT_SCREEN.name -> ABOUT_SCREEN
                POKEMON_SCREEN.name -> POKEMON_SCREEN
                FAVOURITES_SCREEN.name -> FAVOURITES_SCREEN
                null -> HOME_SCREEN
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}