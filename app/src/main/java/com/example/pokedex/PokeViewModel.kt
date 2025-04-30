package com.example.pokedex

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject //for hilt dependencies use the dagger hilt package
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@HiltViewModel
class PokeViewModel @Inject constructor() : ViewModel(){
    private val _showImage = MutableStateFlow(false)
    val showImage : StateFlow<Boolean> = _showImage

    fun setShowImage(value:Boolean){
        _showImage.value = value
    }
}