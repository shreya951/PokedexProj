package com.example.pokedex

import android.util.Log
import com.example.pokedex.PokeAPIService
import com.example.pokedex.PokemonResult
import javax.inject.Inject


class PokeRepository @Inject constructor(
    private val api: PokeAPIService
) {
    suspend fun getPokemonList(): List<PokemonResult> {
        Log.d("ASDF", "Checking Repo")
        //getting a list of 20 pokemons from the api (endpoint at path following base url)
        return api.getPokemonList(1302).results
    }
}
