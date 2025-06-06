package com.example.pokemonlibrary.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.navigation.PokemonScreens
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreenButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = Color.White,
    cardColor: CardColors,
    width: Dp = 120.dp,
    height: Dp = 60.dp,
    onClick: () -> Unit = {}
){
    val scope = rememberCoroutineScope()
    var enabled by remember { mutableStateOf(true) }

    Card(
        modifier = modifier.clickable(enabled = enabled) {
            onClick()
            enabled = false
            scope.launch {
                delay(1000)
                enabled = true
            }
        }
            .width(width)
            .height(height),
        shape = RoundedCornerShape(15.dp),
        colors = cardColor,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center),
            color = textColor)
    }
}

@Composable
fun BottomAppBar(navController: NavController) {
    BottomAppBar(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(40.dp)),
        containerColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.menu_icon),
                contentDescription = "Menu Icon",
                modifier = Modifier.clickable {
                    navController.navigate(PokemonScreens.POKEMON_SCREEN.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }},
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                painter = painterResource(id = R.drawable.poke_ball),
                contentDescription = "PokeBall Image",
                modifier = Modifier.size(75.dp).clickable {
                    navController.navigate(PokemonScreens.HOME_SCREEN.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                },
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(
                painter = painterResource(id = R.drawable.favourite_icon),
                contentDescription = "PokeBall Image",
                modifier = Modifier.size(45.dp).padding(bottom = 5.dp).clickable {
                    navController.navigate(PokemonScreens.FAVOURITES_SCREEN.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                tint = Color.Unspecified
            )
        }
    }
}