package com.example.pokedex

import android.util.Log
import com.example.pokedex.PokeAPIService
import com.example.pokedex.PokemonResult
import javax.inject.Inject

class PokeRepository @Inject constructor(
    private val api: PokeAPIService
) {
    suspend fun getPokemonList(): List<PokemonResult> {
        val response =  api.getPokemonList(limit = 20).results
        Log.d("ASDF", "$response")
        return response
    }
}