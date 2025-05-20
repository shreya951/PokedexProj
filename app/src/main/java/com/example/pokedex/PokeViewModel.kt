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

    //private val _uiState = MutableStateFlow(PokeScreenUIState())
    //val showImage: StateFlow<PokeScreenUIState> = _uiState.asStateFlow()


    fun fetchPokemon() {
        viewModelScope.launch {

            try {

                val result = pokeRepository.getPokemonList()
                _pokemonList.value = result  // Populate the Pokemon list
                Log.d("ASDF", "Fetched Pokémon: $result[0].name")
            } catch (e: Exception) {
                Log.d("ASDF", "Failed to fetch Pokémon: $e")
            }

            Log.d("ASDF", "Hello World")
        }
    }


    fun setShowImage(value: Boolean) {
        _showImage.update { pokeState ->
            pokeState.copy(showPokeball = value)
        }
    }
}




    /*
 fun fetchPokemon(){
     viewModelScope.launch{
         Log.d("ASDF", "fetchPokemon() called")
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

*/