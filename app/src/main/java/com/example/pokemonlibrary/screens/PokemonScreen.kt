package com.example.pokemonlibrary.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.BottomAppBar

@Composable
fun PokemonScreen(navController: NavController){
    Box{
        Scaffold(
            bottomBar = { BottomAppBar(navController = navController) }
        ) { paddingValues ->
        }
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.pokemon_logo),
            contentDescription = "Pokemon desc",
            modifier = Modifier.width(200.dp).height(160.dp).padding(0.dp).align(Alignment.TopCenter),
            tint = Color.Unspecified
        )
    }
}