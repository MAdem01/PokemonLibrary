package com.example.pokemonlibrary.model.viewModel.pokemonViewModel

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonlibrary.data.repository.PokemonRepository
import com.example.pokemonlibrary.model.room.PokemonEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonViewModel(private val repository: PokemonRepository) : ViewModel() {

    private val _randomPokemon = MutableStateFlow<PokemonEntity?>(null)
    val randomPokemon: StateFlow<PokemonEntity?> get() = _randomPokemon

    private val _pokemons = MutableStateFlow<List<PokemonEntity>>(emptyList())
    val pokemons: StateFlow<List<PokemonEntity>> get() = _pokemons

    private val _selectedPokemon = MutableStateFlow<PokemonEntity?>(null)
    val selectedPokemon: StateFlow<PokemonEntity?> get() = _selectedPokemon

    private val _isRandomPokemon = MutableStateFlow<Boolean>(true)
    val isRandomPokemon: StateFlow<Boolean> get() = _isRandomPokemon

    private val _searchValue = mutableStateOf("")
    val searchValue: State<String> get() = _searchValue

    private val _randomPokemonEvolutionList = MutableStateFlow<MutableList<PokemonEntity?>>(
        mutableListOf()
    )
    val randomPokemonEvolutionList: StateFlow<MutableList<PokemonEntity?>> get() = _randomPokemonEvolutionList

    private val _selectedPokemonEvolutionList = MutableStateFlow<MutableList<PokemonEntity?>>(
        mutableListOf()
    )
    val selectedPokemonEvolutionList: StateFlow<MutableList<PokemonEntity?>> get() = _selectedPokemonEvolutionList

    private val _page = mutableIntStateOf(1)
    val page: State<Int> get() = _page

    private val _offset = mutableIntStateOf(0)
    val offset: State<Int> get() = _offset

    init {
        loadRandomPokemon()
        loadPokemons(_offset.intValue)
    }

    fun loadRandomPokemon() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val pokemon = repository.getRandomPokemon()
                val evolutionList = pokemon.evolutionChain.map {
                    repository.getPokemonByName(it)
                }

                _randomPokemon.value = pokemon
                _randomPokemonEvolutionList.value = evolutionList.toMutableList()
            }
        }
    }


    fun loadPokemons(offset: Int = _offset.intValue) {
        if(searchValue.value.isEmpty()) {
            viewModelScope.launch {
                _pokemons.value = withContext(Dispatchers.IO) {
                    repository.getPokemons(offset)
                }
            }
        }else{
            viewModelScope.launch {
                _pokemons.value = withContext(Dispatchers.IO) {
                    repository.getPokemonsWithSearchValue(offset, searchValue.value)
                }
            }
        }
    }

    fun loadSelectedPokemon(pokemonName: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val pokemon = repository.getPokemonByName(pokemonName)
                val evolutionList = pokemon.evolutionChain.map {
                    repository.getPokemonByName(it)
                }

                _selectedPokemon.value = pokemon
                _selectedPokemonEvolutionList.value = evolutionList.toMutableList()
            }
        }
    }

    fun loadNextPage() {
        _page.intValue++
        _offset.intValue += 16
        loadPokemons(_offset.intValue)
    }

    fun loadPreviousPage() {
        if (_page.intValue > 1) {
            _page.intValue--
            _offset.intValue -= 16
            loadPokemons(_offset.intValue)
        }
    }

    fun toggleIsRandomPokemonToFalse(){
        _isRandomPokemon.value = false
    }

    fun toggleIsRandomPokemonToTrue(){
        _isRandomPokemon.value = true
    }

    fun setSearchValue(value: String){
        _searchValue.value = value
    }
}
