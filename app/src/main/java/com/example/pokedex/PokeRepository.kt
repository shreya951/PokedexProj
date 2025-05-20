package com.example.pokedex

import android.util.Log
import com.example.pokedex.PokeAPIService
import com.example.pokedex.PokemonResult
import javax.inject.Inject

/*
class PokeRepository @Inject constructor(
    private val api: PokeAPIService
) {

    suspend fun getPokemonList(): List<PokemonResult> {
        val response =  api.getPokemonList(100).results
        Log.d("ASDF", "$response")
        return response
    }
}

 */

class PokeRepository @Inject constructor(
    private val api: PokeAPIService
) {
    suspend fun getPokemonList(): List<PokemonResult> {
        Log.d("ASDF", "Checking Repo")
        return api.getPokemonList(10).results
    }
}
