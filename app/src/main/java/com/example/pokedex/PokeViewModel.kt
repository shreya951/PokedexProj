package com.example.pokedex

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.PokeAPIService
import com.example.pokedex.PokemonResult
import com.example.pokedex.PokeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject //for hilt dependencies use the dagger hilt package
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class PokeViewModel @Inject constructor(
    private val pokeRepository : PokeRepository
)  : ViewModel() {


    private val _showImage = MutableStateFlow(PokeScreenUIState())
    val showImage = _showImage.asStateFlow()

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList: StateFlow<List<PokemonResult>> = _pokemonList

    fun fetchPokemon() {
        viewModelScope.launch {
            kotlin.runCatching {
                pokeRepository.getPokemonList()
            }.onSuccess { result ->
                _pokemonList.value = result
                pickAnotherRandomPokemon() // set an initial random pokemon
                Log.d("ASDF", "Received pokemon: $pokemonList")
            }.onFailure { error ->
                Log.d("ASDF", "Failed: $error")
            }
        }

        Log.d("ASDF", "Checking fetchPokemon function")
    }


    //used to update ui state with one changed variable
    fun setShowImage(value: Boolean) {
        _showImage.update { pokeState ->
            pokeState.copy(showPokeball = value)
        }
    }


    fun pickAnotherRandomPokemon() {
        val list = _pokemonList.value
        if (list.isNotEmpty()) {
            val num = (0 until list.size - 1).random()
            //making a copy of the current state of showImage
            val currentState = _showImage.value
            //then change the randomIndex to a new number
            val newState = currentState.copy(randomIndex = num)
            //update showImage
            _showImage.value = newState
        }
    }
}

