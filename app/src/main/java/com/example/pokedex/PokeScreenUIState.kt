package com.example.pokedex

data class PokeScreenUIState(
    val showPokeball : Boolean = false,
    val pokemonList: List<String> = emptyList(),
    val randomIndex : Int = -1
)