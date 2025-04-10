package com.example.pokemonlibrary.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.BottomBarContent
import com.example.pokemonlibrary.model.viewModel.PokemonViewModel
import com.example.pokemonlibrary.utils.getScreenColor

@Composable
fun AboutScreen(navController: NavController, pokemonViewModel: PokemonViewModel){
    val pokemonData by pokemonViewModel.pokemonData.collectAsState()
    val pokemonSpeciesData by pokemonViewModel.pokemonSpeciesData.collectAsState()

    val screenColor = pokemonSpeciesData?.color?.name?.let {
        colorResource(getScreenColor(it))
    }
    val titleColor = screenColor?.copy(
        red = screenColor.red * 1.2f,
        green = screenColor.green * 1.2f,
        blue = screenColor.blue * 1.2f
    )

    val shadowColor = screenColor?.copy(
        red = screenColor.red * 1.1f,
        green = screenColor.green * 1.1f,
        blue = screenColor.blue * 1.1f
    )

    screenColor?.let {
        Box{
            Scaffold(
                bottomBar = {
                    BottomAppBar(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(40.dp)),
                        containerColor = Color.White
                    ){
                        BottomBarContent()
                    }
                },
                containerColor = screenColor
            ) {paddingValues ->
                Surface(
                    modifier = Modifier.padding().fillMaxSize(),
                    color = screenColor) {
                    if (titleColor != null) {
                        Surface(
                            modifier = Modifier
                                .padding(start = 5.dp, end = 5.dp, top = 275.dp)
                                .height(150.dp),
                            color = titleColor,
                            shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center) {
                                if(shadowColor != null){
                                    Surface(
                                        modifier = Modifier
                                            .height(15.dp)
                                            .width(60.dp),
                                        color = shadowColor,
                                        shape = GenericShape { size, _ ->
                                            moveTo(0f, size.height / 2)
                                            cubicTo(size.width / 6f, 0f,
                                                5 * size.width / 6f, 0f,
                                                size.width, size.height / 2)
                                            cubicTo(5 * size.width / 6f, size.height,
                                                size.width / 6f, size.height,
                                                0f, size.height / 2)
                                        },
                                    ){}
                                }
                            }
                        }
                    }
                }
            }
            AsyncImage(
                model = pokemonData?.sprites?.other?.officialArtwork?.frontShiny,
                contentDescription = "Pokemon Image",
                modifier = Modifier.size(450.dp).align(Alignment.Center).padding(bottom = 300.dp)
            )
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

}
