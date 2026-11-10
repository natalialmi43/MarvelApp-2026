package edu.iesam.marvelapp.features.superheroes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.iesam.marvelapp.features.superheroes.domain.GetSuperHeroesUseCase
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero

class SuperHeroListViewModel () : ViewModel(){


    private val _uiState= MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState
    fun loadSuperHeroes() {
        // 1. Mostramos el loading
        _uiState.value = UiState(isLoading = true)

        // 2. Mostrar datos
        _uiState.value = UiState(superHeroes = emptyList())


    }

    data class UiState(
        val isLoading: Boolean = false,
        val hasError: Boolean = false,
        val superHeroes : List<SuperHero>? = null

    ){

    }
}