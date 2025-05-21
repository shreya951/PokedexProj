package com.example.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PokeScreen(viewModel: PokeViewModel = hiltViewModel()) {

    //using by means you dont need to do .value
    val uiState by viewModel.showImage.collectAsStateWithLifecycle()
    val pokemonList by viewModel.pokemonList.collectAsStateWithLifecycle()

    PokeScreenContent(
        //essentially passing what we already have
        uiState = uiState,
        pokemonList = pokemonList,
        onShowPokeballClicked = {
            viewModel.setShowImage(true)
            viewModel.pickAnotherRandomPokemon()
        },
        onHidePokeballClicked = {
            viewModel.setShowImage(false)
        }
    )
}


@Composable
fun PokeScreenContent(
    uiState: PokeScreenUIState,
    pokemonList: List<PokemonResult>,
    //() means it takes no arguments, and Unit means it returns a void value
    onShowPokeballClicked: () -> Unit,
    onHidePokeballClicked: () -> Unit
) {
    val babyBlue = Color(0XFFB9DFF7)

    //when making a custom color, instead of doing color = Color.ColorName, do color = CustomColor
    Box(modifier = Modifier.fillMaxSize().background(color = babyBlue)) {
        if (uiState.showPokeball) {
            val i = uiState.randomIndex
            val pokemon = pokemonList[i]
            Text(
                //call attributes of pokemon bc we are getting a single pokemon from the list
                text = "Name: ${pokemon.name}\nURL: ${pokemon.url}",
                //modifier is essentially for spacing
                modifier = Modifier.align(Alignment.Center).padding(16.dp)
            )
        }

        FilledTonalButton(
            onClick = onShowPokeballClicked,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(30.dp)
        ) {
            Text("Show a Pokemon!")
        }

        FilledTonalButton(
            onClick = onHidePokeballClicked,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(30.dp)
        ) {
            Text("Hide Pokemon")
        }
    }
}



@Composable
fun PokeScreenContentPreview() {
    val samplePokemonList = listOf(
        PokemonResult(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
        PokemonResult(name = "charmander", url = "https://pokeapi.co/api/v2/pokemon/4/"),
        PokemonResult(name = "squirtle", url = "https://pokeapi.co/api/v2/pokemon/7/")
    )

    PokeScreenContent(
        uiState = PokeScreenUIState(showPokeball = true, randomIndex = 0),
        pokemonList = samplePokemonList,
        onShowPokeballClicked = {},
        onHidePokeballClicked = {}
    )
}
