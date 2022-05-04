package com.example.pokedex.models

import android.net.Uri

data class Pokemon(
    val index: String,
    val name: String,
    val type: String,
    val icon: String,
    val height: Int,
    val weight: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int
)

