package com.example.pokemonlibrary.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.BottomBarContent
import com.example.pokemonlibrary.model.viewModel.PokemonViewModel

@Composable
fun AboutScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    Box{
        Scaffold(
            bottomBar = {
                BottomBarContent()
            }
        ) {paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) { }
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Arrow Back",
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    navController.popBackStack()
                }
                .padding(start = 20.dp, top = 50.dp)
                .align(Alignment.TopStart)
        )
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.pokemon_logo),
            contentDescription = "Pokemon desc",
            modifier = Modifier.width(200.dp).height(160.dp).align(Alignment.TopCenter),
            tint = Color.Unspecified
        )
    }
}