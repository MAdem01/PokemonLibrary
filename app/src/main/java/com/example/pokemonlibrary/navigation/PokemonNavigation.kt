package com.example.pokemonlibrary.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.model.room.PokemonDatabase
import com.example.pokemonlibrary.model.viewModel.pokemonViewModel.PokemonViewModel
import com.example.pokemonlibrary.screens.AboutScreen
import com.example.pokemonlibrary.screens.HomeScreen
import com.example.pokemonlibrary.screens.PokemonScreen

@Composable
fun PokemonNavigation(){
    val context = LocalContext.current
    val database = remember { PokemonDatabase.getDatabase(context) }
    val repository = remember { PokemonRepository(database.pokemonDao()) }
    val pokemonViewModel = remember { PokemonViewModel(repository) }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PokemonScreens.HOME_SCREEN.name){
        composable(PokemonScreens.HOME_SCREEN.name){
            HomeScreen(navController = navController, pokemonViewModel = pokemonViewModel)
        }
        composable(PokemonScreens.ABOUT_SCREEN.name){
            AboutScreen(navController = navController, pokemonViewModel = pokemonViewModel)
        }
        composable(PokemonScreens.POKEMON_SCREEN.name){
            PokemonScreen(navController = navController, pokemonViewModel = pokemonViewModel)
        }
        composable(PokemonScreens.FAVOURITES_SCREEN.name){

        }
    }
}