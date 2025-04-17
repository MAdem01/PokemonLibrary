package com.example.pokemonlibrary.model.viewModel.pokemonsViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemonlibrary.data.repository.PokemonRepository

class PokemonsViewModelFactory(private val pokemonRepository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonsViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
