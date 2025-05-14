package com.example.pokedex

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeResponseDto(
    @Json(name = "results") val results: List<PokemonResult>
)

@JsonClass(generateAdapter = true)
data class PokemonResult(
    @Json(name = "name") val name: String,
    @Json(name = "url")  val url: String
)