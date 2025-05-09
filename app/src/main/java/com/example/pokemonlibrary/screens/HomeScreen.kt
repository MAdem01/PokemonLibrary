package com.example.pokemonlibrary.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.BottomAppBar
import com.example.pokemonlibrary.model.viewModel.pokemonViewModel.PokemonViewModel
import com.example.pokemonlibrary.navigation.PokemonScreens
import com.example.pokemonlibrary.utils.getScreenColor
import com.example.pokemonlibrary.widgets.HomeScreenButtons
import com.example.pokemonlibrary.widgets.PokemonTitle

@Composable
fun HomeScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    val pokemonData by pokemonViewModel.randomPokemon.collectAsState()
    val screenColor = pokemonData?.color?.let {
        colorResource(getScreenColor(it))
    }
    val titleColor = screenColor?.copy(
        red = screenColor.red * 1.2f,
        green = screenColor.green * 1.2f,
        blue = screenColor.blue * 1.2f
    )

    screenColor?.let{
        Box{
            Scaffold(
                bottomBar = { BottomAppBar(navController = navController)},
                containerColor = screenColor
            ) { paddingValues ->
                Surface(
                    modifier = Modifier.padding(paddingValues).fillMaxSize(),
                    color = screenColor
                ) {
                    Column(
                        modifier = Modifier.padding(top = 155.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PokemonTitle(pokemonData = pokemonData, titleColor = titleColor)
                        AsyncImage(
                            model = pokemonData?.sprites?.official_artwork,
                            contentDescription = "Pokemon Image",
                            modifier = Modifier.height(300.dp).fillMaxWidth().padding()
                        )
                        HomeScreenButtons(pokemonViewModel = pokemonViewModel){
                            pokemonViewModel.toggleIsRandomPokemonToTrue()
                            navController.navigate(route = PokemonScreens.ABOUT_SCREEN.name)
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
}



