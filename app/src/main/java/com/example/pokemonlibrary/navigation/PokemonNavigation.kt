package com.example.pokemonlibrary.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.data.retrofitClient.RetroFitClient
import com.example.pokemonlibrary.model.viewModel.pokemonViewModel.PokemonViewModel
import com.example.pokemonlibrary.model.viewModel.pokemonViewModel.PokemonViewModelFactory
import com.example.pokemonlibrary.model.viewModel.pokemonsViewModel.PokemonsViewModel
import com.example.pokemonlibrary.model.viewModel.pokemonsViewModel.PokemonsViewModelFactory
import com.example.pokemonlibrary.screens.AboutScreen
import com.example.pokemonlibrary.screens.HomeScreen
import com.example.pokemonlibrary.screens.PokemonScreen

@Composable
fun PokemonNavigation(){
    val pokemonService = RetroFitClient.api
    val pokemonRepository = PokemonRepository(pokemonService)
    val pokemonViewModel: PokemonViewModel = viewModel(
        factory = PokemonViewModelFactory(pokemonRepository)
    )
    val pokemonsViewModel: PokemonsViewModel = viewModel(
        factory = PokemonsViewModelFactory(pokemonRepository)
    )

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PokemonScreens.HOME_SCREEN.name){
        composable(PokemonScreens.HOME_SCREEN.name){
            HomeScreen(navController = navController, pokemonViewModel = pokemonViewModel)
        }
        composable(PokemonScreens.ABOUT_SCREEN.name){
            AboutScreen(navController = navController, pokemonViewModel = pokemonViewModel)
        }
        composable(PokemonScreens.POKEMON_SCREEN.name){
            PokemonScreen(navController = navController, pokemonsViewModel = pokemonsViewModel)
        }
        composable(PokemonScreens.FAVOURITES_SCREEN.name){

        }
    }
}