package com.example.pokedex.models

import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: String,
    val name: String,
    @SerializedName ("types") val types: List<Type>,
    @SerializedName ("stats") val stats: List<Stat>,
    val sprites: Sprites,
    val height: Int,
    val weight: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int
)

data class Sprites (
    @Expose @SerializedName("other") val other: Other
)

data class Other (
    @Expose @SerializedName("official-artwork") val official: Official
)

data class Official (
    @Expose @SerializedName("front_default") val frontDefault: String?
)

data class Type (
    @Expose @SerializedName("type") val type: Name
)

data class Name (
    @Expose @SerializedName("name") val name: String?
)

data class Stat (
    @Expose @SerializedName("base_stat") val stat: Int
)
