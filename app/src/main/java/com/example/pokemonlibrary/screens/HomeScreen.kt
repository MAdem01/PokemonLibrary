package com.example.pokemonlibrary.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pokemonlibrary.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    Box{
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row{
                            Text(
                                text = "Pokémons",
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 19.sp)
                            Spacer(modifier = Modifier.width(160.dp))
                            Text(text = "Favourites",
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontFamily = FontFamily.Monospace,
                                fontSize = 19.sp)
                        }
                    }
                )
            }
        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) { }
        }
        HorizontalDivider(
            modifier = Modifier.padding(top = 55.dp),
            color = Color.Black,
            thickness = 5.dp)
        Image(
            painter = painterResource(id = R.drawable.poke_ball),
            contentDescription = "PokeBall Image",
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .padding(top = 12.dp)
            )
    }
}