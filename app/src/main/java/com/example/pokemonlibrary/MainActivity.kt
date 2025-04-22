package com.example.pokemonlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokemonlibrary.model.room.PokemonDatabase
import com.example.pokemonlibrary.navigation.PokemonNavigation
import com.example.pokemonlibrary.ui.theme.PokemonLibraryTheme
import com.example.pokemonlibrary.utils.preloadDatabaseIfNeeded

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preloadDatabaseIfNeeded(this, PokemonDatabase.getDatabase(this))
        enableEdgeToEdge()
        setContent {
            MyApp {
                PokemonNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    PokemonLibraryTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        PokemonNavigation()
    }
}