package com.example.pokedex.utils

import com.example.pokedex.models.Pokemon

interface OnPokemonClickListener {
    fun onPokemonItemCliked(pokemon: Pokemon)
    fun onPokemonSearch(pokemon: Pokemon)
    fun showAlert(msg : String)
}