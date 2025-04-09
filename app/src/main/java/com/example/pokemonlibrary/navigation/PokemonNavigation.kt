package com.example.pokemonlibrary.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PokemonNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PokemonScreens.HOME_SCREEN.name){
        composable(PokemonScreens.HOME_SCREEN.name){

        }
        composable(PokemonScreens.POKEMON_SCREEN.name){

        }
        composable(PokemonScreens.FAVOURITES_SCREEN.name){

        }
    }
}