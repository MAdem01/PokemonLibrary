package com.example.pokemonlibrary.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokemonlibrary.R
import com.example.pokemonlibrary.components.HomeScreenButton
import com.example.pokemonlibrary.model.PokemonResponse
import com.example.pokemonlibrary.model.room.PokemonEntity
import com.example.pokemonlibrary.model.viewModel.pokemonViewModel.PokemonViewModel
import com.example.pokemonlibrary.utils.capitalizeFirstLetter
import com.example.pokemonlibrary.utils.getPokemonTypeIcon

@Composable
fun HomeScreenButtons(pokemonViewModel: PokemonViewModel, onAboutClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 70.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        HomeScreenButton(
            text = "ABOUT",
            cardColor = CardDefaults.cardColors(containerColor = Color(0XFF1b1b1b))
        ){
            onAboutClick()
        }
        Spacer(modifier = Modifier.width(20.dp))
        HomeScreenButton(
            text = "+",
            textColor = Color.Black,
            cardColor = CardDefaults.cardColors(containerColor = Color(0XFFffe570)),
            width = 60.dp,
            height = 60.dp
        )
        Spacer(modifier = Modifier.width(20.dp))
        HomeScreenButton(
            text = "ROLL",
            cardColor = CardDefaults.cardColors(containerColor = Color(0XFF1b1b1b))
        ){
            pokemonViewModel.loadRandomPokemon()
        }
    }
}

@Composable
fun PokemonTitle(pokemonData: PokemonEntity?, titleColor: Color?) {
    Column(
        modifier = Modifier.padding(start = 50.dp, end = 50.dp, bottom = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pokemonData?.name?.let {
            Text(
                text = it.capitalizeFirstLetter(),
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                color = titleColor!!
            )
        }
        Row {
            pokemonData?.types?.get(0)?.let {
                Icon(
                    imageVector = ImageVector.vectorResource(getPokemonTypeIcon(it)),
                    contentDescription = "Pokemon Icon",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = it.capitalizeFirstLetter(),
                )
            }
        }
    }
}

@Composable
fun StatCard(statName: String, statValue: String, statIcon: Int, iconColor: Color){
    Card(
        modifier = Modifier.width(80.dp).height(110.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = statIcon),
                contentDescription = "Stat Icon",
                modifier = Modifier.size(35.dp).fillMaxWidth().padding(bottom = 5.dp, top = 5.dp),
                tint = iconColor
            )
            Text(
                text = statName,
                color = Color.LightGray,
            )
            Text(
                text = statValue,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
        }
    }
}

@Composable
fun StatRow(pokemonData: PokemonEntity){
    Row{
        StatCard(statName = "Attack", statValue = pokemonData.stats[1].base_stat.toString(), statIcon = getPokemonTypeIcon(
            pokemonData.types[0]), iconColor = Color.Unspecified)
        Spacer(modifier = Modifier.width(10.dp))
        StatCard(statName = "Defense", statValue = pokemonData.stats[2].base_stat.toString(), statIcon = R.drawable.defense_stat_icon, iconColor = Color.Blue)
        Spacer(modifier = Modifier.width(10.dp))
        StatCard(statName = "Health", statValue = pokemonData.stats[0].base_stat.toString(), statIcon = R.drawable.health_stat_icon, iconColor = Color.Red)
        Spacer(modifier = Modifier.width(10.dp))
        StatCard(statName = "Speed", statValue = pokemonData.stats[5].base_stat.toString(), statIcon = R.drawable.speed_stat_icon, iconColor = Color.Green)
    }
}

@Composable
fun DescriptionCard(buttonColor: Color, flavorTextEntry: String, onEvolutionClick: () -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().height(250.dp).padding(top = 20.dp, start = 15.dp, end = 15.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = flavorTextEntry)
            Row {
                HomeScreenButton(
                    text = "ADD",
                    cardColor = CardDefaults.cardColors(buttonColor)
                )
                Spacer(modifier = Modifier.width(40.dp))
                HomeScreenButton(
                    text = "EVOLUTIONS",
                    cardColor = CardDefaults.cardColors(buttonColor)
                ){
                    onEvolutionClick()
                }
            }
        }
    }
}

@Composable
fun PokemonScreenCards(pokemonData: List<PokemonEntity>, onClick: (String) -> Unit){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        pokemonData.chunked(4).map{ pokemons ->
            Row {
                pokemons.map{pokemon -> PokemonScreenCard(pokemon = pokemon){
                    onClick(pokemon.name)
                } }
            }
        }
    }
}

@Composable
fun PokemonScreenCard(pokemon: PokemonEntity, onClick: () -> Unit){
    Card(
        modifier = Modifier.height(130.dp).width(90.dp).padding(3.dp).clickable {
            onClick()
        },
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color(0XFFFDFFF5)),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(width = 1.dp, Color.Black)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = pokemon.sprites.front_shiny,
                contentDescription = "Pokemon Image",
                modifier = Modifier.size(90.dp)
            )
            Text(
                text = pokemon.name,
                fontSize = 10.sp)
        }
    }
}


@Composable
fun PageController(pokemonViewModel: PokemonViewModel, searchValue: String) {
    Row(
        modifier = Modifier.padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back Arrow",
            modifier = Modifier.size(35.dp).clickable {
                pokemonViewModel.loadPreviousPage()
            })
        Text(
            text = pokemonViewModel.page.value.toString(),
            modifier = Modifier.size(35.dp).padding(top = 5.dp),
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Forward Arrow",
            modifier = Modifier.size(35.dp).clickable {
                pokemonViewModel.loadNextPage()
            })

    }
}


@Composable
fun EvolutionChart(evolutionData: MutableList<PokemonEntity?>, shadowColor: Color?, backgroundColor: Color, onBackClick: () -> Unit){
    Surface(
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 15.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        color = backgroundColor,
    ) {
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Back Arrow",
                modifier = Modifier.clickable {
                    onBackClick()
                },
                tint = Color.Black
            )
            LazyRow(
                modifier = Modifier.padding(top = 15.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(items = evolutionData){ index, pokemon ->
                    EvolutionCard(index, pokemon, shadowColor)
                }
            }
        }
    }
}

@Composable
private fun EvolutionCard(
    index: Int,
    pokemon: PokemonEntity?,
    shadowColor: Color?
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Stage ${index + 1}",
            fontSize = 15.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
        AsyncImage(
            model = pokemon?.sprites?.official_artwork,
            contentDescription = "Pokemon Image",
            modifier = Modifier.size(120.dp)
        )
        Surface(
            modifier = Modifier
                .height(23.5.dp)
                .width(100.dp),
            color = shadowColor!!,
            shape = GenericShape { size, _ ->
                moveTo(0f, size.height / 2)
                cubicTo(
                    size.width / 6f, 0f,
                    5 * size.width / 6f, 0f,
                    size.width, size.height / 2
                )
                cubicTo(
                    5 * size.width / 6f, size.height,
                    size.width / 6f, size.height,
                    0f, size.height / 2
                )
            },
        ) {}
        if (pokemon != null) {
            Text(
                text = pokemon.name.capitalizeFirstLetter(),
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                color = Color.White
            )
        }
    }
}