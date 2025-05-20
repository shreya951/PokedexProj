package com.example.pokedex


import retrofit2.http.GET


import retrofit2.http.Query

interface PokeAPIService {
    @GET("pokemon")
    suspend fun getPokemonList(@Query("limit") limit: Int): PokeResponseDto

}
