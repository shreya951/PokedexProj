package com.example.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedex.R
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment


@Composable
fun PokeScreen(){
    PokeScreenContent()
}

@Composable
fun PokeScreenContent(){
    val babyBlue = Color(0XFFB9DFF7)
    var showImage by remember {mutableStateOf(false)}

    Box(modifier = Modifier.fillMaxSize().background(color = babyBlue)){
    //when making a custom color, instead of doing color = Color.ColorName, do color = CustomColor

    if(showImage) {
        Image(
            painter = painterResource(id = R.drawable.andstudiopoke),
            contentDescription = "Image of android studio logo combined with the pokemon symbol",
            modifier = Modifier.fillMaxWidth().align(Alignment.Center)
        )
    }

    FilledTonalButton(
        onClick = { showImage = true },
        modifier = Modifier.align(Alignment.BottomCenter).padding(30.dp)
    ) { Text("Click to show image!") }

    FilledTonalButton(
         onClick = {showImage = false},
         modifier = Modifier.align(Alignment.TopCenter).padding(30.dp)
    ) { Text("Click to remove image!") }

    }

}


@Composable
fun PokeScreenContentPreview(){
    PokeScreenContent()
}