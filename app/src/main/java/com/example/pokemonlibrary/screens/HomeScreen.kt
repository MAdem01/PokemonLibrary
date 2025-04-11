package com.example.pokemonlibrary.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.data.retrofitClient.RetroFitClient
import com.example.pokemonlibrary.model.viewModel.PokemonViewModel
import com.example.pokemonlibrary.model.viewModel.PokemonViewModelFactory
import com.example.pokemonlibrary.widgets.BottomBarContent
import com.example.pokemonlibrary.widgets.EvolutionChain
import com.example.pokemonlibrary.widgets.PokemonImageRow
import com.example.pokemonlibrary.widgets.PokemonStatCard

@Preview
@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    val pokemonService = RetroFitClient.api
    val pokemonRepository = PokemonRepository(pokemonService)
    val pokemonViewModel: PokemonViewModel = viewModel(
        factory = PokemonViewModelFactory(pokemonRepository)
    )
    val pokemonData by pokemonViewModel.pokemonData.collectAsState()
    val pokemonEvolutionChainImages by pokemonViewModel.pokemonEvolutionChainImages.collectAsState()
    val imageSliderToggle = remember { mutableStateOf(false)}
    val evolutionSliderToggle = remember { mutableStateOf(false)}

    Box{
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(20.dp)),
                    containerColor = Color.White
                ){
                    BottomBarContent()
                }
            }
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                Column {
                    PokemonStatCard(pokemonData = pokemonData)
                    Spacer(modifier = Modifier
                        .height(15.dp))
                    PokemonImageRow(pokemonData = pokemonData, imageSliderToggle = imageSliderToggle)
                    EvolutionChain(evolutionSliderToggle = evolutionSliderToggle, pokemonEvolutionChainImages = pokemonEvolutionChainImages)
                }
            }
        }
    }
}