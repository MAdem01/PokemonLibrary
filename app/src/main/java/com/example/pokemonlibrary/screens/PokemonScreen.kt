package com.example.pokemonlibrary.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.BottomAppBar
import com.example.pokemonlibrary.model.viewModel.pokemonsViewModel.PokemonsViewModel
import com.example.pokemonlibrary.widgets.PokemonScreenCards

@Composable
fun PokemonScreen(navController: NavController, pokemonsViewModel: PokemonsViewModel){
    val pokemonsData by pokemonsViewModel.pokemonsData.collectAsState()

    Box{
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) }
        ) { paddingValues ->
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                pokemonsData?.let {pokemonsData -> PokemonScreenCards(pokemonsData) }
            }
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.pokemon_logo),
            contentDescription = "Pokemon desc",
            modifier = Modifier.width(200.dp).height(160.dp).padding(0.dp).align(Alignment.TopCenter),
            tint = Color.Unspecified
        )
    }
}