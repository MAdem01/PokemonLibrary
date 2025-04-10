package com.example.pokemonlibrary.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.pokemonlibrary.model.PokemonResponse
import com.example.pokemonlibrary.utils.capitalizeFirstLetter

@Composable
fun PokemonStatCard(pokemonData: PokemonResponse?){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(top = 45.dp, start = 10.dp, end = 10.dp),
        border = BorderStroke(width = 1.dp, Color.Black),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(modifier = Modifier.padding(bottom = 15.dp, end = 10.dp),
            horizontalArrangement = Arrangement.Start){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                AsyncImage(
                    modifier = Modifier.size(130.dp),
                    model = pokemonData?.sprites?.other?.officialArtwork?.frontShiny,
                    contentDescription = "Pokemon Image"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Card(
                    modifier = Modifier.padding(5.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RectangleShape,
                    border = BorderStroke(width = 1.dp, color = Color.Black)
                ) {
                    pokemonData?.species?.name?.let { Text(
                        text = it.capitalizeFirstLetter(),
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp) }
                }
            }
            VerticalDivider(
                modifier = Modifier.width(4.dp).padding(top = 15.dp),
                color = Color.Black)
            Column {
                Text(
                    text = "Stats",
                    fontWeight = FontWeight.SemiBold
                )
                LazyColumn {
                    pokemonData?.let {
                        items(it.stats){ item ->
                            Text(text = item.stat.name)
                            LinearProgressIndicator(
                                progress = {
                                    item.baseStat / 100f
                                },
                                modifier = Modifier.height(10.dp),
                                strokeCap = StrokeCap.Round
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonImageRow(pokemonData: PokemonResponse?, imageToggle: MutableState<Boolean>){
    Card(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black)
    ) {
        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                text = "Pokemon Images",
                fontSize = 20.sp)
            Icon(
                imageVector = if(imageToggle.value) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Down Arrow",
                modifier = Modifier.clickable {
                    imageToggle.value = !imageToggle.value
                })
        }
        if(imageToggle.value){
            HorizontalDivider(modifier = Modifier.height(3.dp), color = Color.Black)
            val pokemonImages = listOf(
                pokemonData?.sprites?.frontShiny,
                pokemonData?.sprites?.backDefault,
                pokemonData?.sprites?.frontShiny,
                pokemonData?.sprites?.backShiny)
            LazyRow{
                items(pokemonImages){item ->
                    VerticalDivider(
                        modifier = Modifier.width(2.dp).height(160.dp),
                        color = Color.Black)
                    AsyncImage(
                        model = item,
                        contentDescription = "Pokemon Image",
                        modifier = Modifier.size(160.dp))
                }
            }
        }
    }
}

@Composable
fun TopBarContent() {
    Row {
        Text(
            text = "Pokémons",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            fontSize = 19.sp
        )
        Spacer(modifier = Modifier.width(160.dp))
        Text(
            text = "Favourites",
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Monospace,
            fontSize = 19.sp
        )
    }
}