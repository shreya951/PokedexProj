package com.example.pokedex


import retrofit2.http.GET


import retrofit2.http.Query

interface PokeAPIService {
    @GET("/api/v2/pokemon/results")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokeResponseDto
}
