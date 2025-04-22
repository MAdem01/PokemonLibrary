package com.example.pokemonlibrary.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.BottomAppBar
import com.example.pokemonlibrary.model.viewModel.pokemonViewModel.PokemonViewModel
import com.example.pokemonlibrary.widgets.PokemonScreenCards

@Composable
fun PokemonScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    val page = remember { mutableIntStateOf(1) }
    val offset = remember { mutableIntStateOf(0) }
    val pokemonsData by pokemonViewModel.pokemons.collectAsState()

    Box{
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) }
        ) { paddingValues ->
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                   modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PokemonScreenCards(pokemonsData)
                    Row(
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back Arrow",
                            modifier = Modifier.clickable {
                                if(page.intValue > 0) {
                                    page.intValue--
                                    offset.value -= 16
                                    pokemonViewModel.loadPokemons(offset = offset.intValue)
                                }
                            })
                        Text(
                            text = page.intValue.toString(),
                            modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Forward Arrow",
                            modifier = Modifier.clickable {
                                    page.intValue++
                                    offset.value += 16
                                pokemonViewModel.loadPokemons(offset = offset.value)
                            })

                     }
                }
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