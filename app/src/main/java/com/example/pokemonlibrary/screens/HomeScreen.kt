package com.example.pokemonlibrary.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.data.retrofitClient.RetroFitClient
import com.example.pokemonlibrary.model.viewModel.PokemonViewModel
import com.example.pokemonlibrary.model.viewModel.PokemonViewModelFactory
import com.example.pokemonlibrary.widgets.PokemonImageRow
import com.example.pokemonlibrary.widgets.PokemonStatCard
import com.example.pokemonlibrary.widgets.TopBarContent

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    val pokemonService = RetroFitClient.api
    val pokemonRepository = PokemonRepository(pokemonService)
    val pokemonViewModel: PokemonViewModel = viewModel(
        factory = PokemonViewModelFactory(pokemonRepository)
    )
    val pokemonData by pokemonViewModel.pokemonData.collectAsState()
    val pokemonEvolutionChainData by pokemonViewModel.pokemonEvolutionChainData.collectAsState()
    val imageToggle = remember { mutableStateOf(false)}

    Box{
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        TopBarContent()
                    }
                )
            }
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                Column {
                    PokemonStatCard(pokemonData = pokemonData)
                    Spacer(modifier = Modifier
                        .height(15.dp))
                    PokemonImageRow(pokemonData = pokemonData, imageToggle = imageToggle)
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 55.dp),
            color = Color.Black,
            thickness = 5.dp)
        Image(
            painter = painterResource(id = R.drawable.poke_ball),
            contentDescription = "PokeBall Image",
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(top = 12.dp)
            )
    }
}