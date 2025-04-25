package com.example.pokemonlibrary.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.BottomAppBar
import com.example.pokemonlibrary.model.viewModel.pokemonViewModel.PokemonViewModel
import com.example.pokemonlibrary.navigation.PokemonScreens
import com.example.pokemonlibrary.widgets.PageController
import com.example.pokemonlibrary.widgets.PokemonScreenCards

@Composable
fun PokemonScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    val pokemonsData by pokemonViewModel.pokemons.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    Row(
                       modifier = Modifier.fillMaxWidth().padding(start = 18.dp, end = 18.dp)
                    ){
                        OutlinedTextField(
                            value = pokemonViewModel.searchValue.value,
                            onValueChange = {pokemonViewModel.setSearchValue(it)},
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Search for pokemons..") },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    pokemonViewModel.loadPokemons(offset = 0)
                                    keyboardController?.hide()
                                }
                            ),
                            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.White)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    PokemonScreenCards(pokemonsData){ pokemon ->
                        pokemonViewModel.loadSelectedPokemon(pokemon)
                        pokemonViewModel.toggleIsRandomPokemonToFalse()
                        navController.navigate(PokemonScreens.ABOUT_SCREEN.name){
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                    PageController(pokemonViewModel, pokemonViewModel.searchValue.value)
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
