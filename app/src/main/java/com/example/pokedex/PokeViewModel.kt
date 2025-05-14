package com.example.pokedex

import android.util.Log
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
   // private val repository : PokeRepository
)  : ViewModel(){

    private val _showImage = MutableStateFlow(PokeScreenUIState())
    val showImage = _showImage.asStateFlow()

    private val _pokemonList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonList : StateFlow<List<PokemonResult>> = _pokemonList


    fun fetchPokemon(){
        viewModelScope.launch{
            kotlin.runCatching {
               // repository.getPokemonList()
            }.onSuccess { pokemonList ->
                Log.d("ASDF", "$pokemonList")
            }.onFailure { error ->
                Log.d("ASDF", "failed $error")
            }
            //val result = repository.getPokemonList()
           // _pokemonList.value = result
        }
    }

    fun setShowImage(value:Boolean){
        _showImage.update { pokeState ->
            pokeState.copy(showPokeball = value)
        }
    }
}


