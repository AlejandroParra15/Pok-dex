package com.example.pokedex.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokedex.POKEMONS_COLLECTION
import com.example.pokedex.USERS_COLLECTION
import com.example.pokedex.adapter.PostAdapter
import com.example.pokedex.service.PokeApiService
import com.google.firebase.firestore.FirebaseFirestore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeInfoViewModel() : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemon(name: String, postAdapter: PostAdapter){
        val call = service.getPokemonInfo(name)

        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    response.body()?.let { pokemon ->
                        postAdapter.addPokemon(pokemon)
                    }
                } else{
                    postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                }

            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                call.cancel()
            }

        })
    }

    fun catchPokemon(name: String, postAdapter: PostAdapter, userName : String){
        val call = service.getPokemonInfo(name)

        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    response.body()?.let { pokemon ->
                        postAdapter.addPokemon(pokemon)
                        var db = FirebaseFirestore.getInstance()
                        db.collection(USERS_COLLECTION).document(userName!!).collection(
                            POKEMONS_COLLECTION
                        ).document(pokemon.name).set(
                            hashMapOf("name" to pokemon.name)
                        )
                    }
                } else{
                    postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                }

            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                call.cancel()
            }

        })
    }

    fun getPokemonStats(name: String, postAdapter: PostAdapter){
        val call = service.getPokemonInfo(name)

        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    response.body()?.let { pokemon ->
                        postAdapter.openStats(pokemon)
                    }
                } else{
                    postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                call.cancel()
            }

        })
    }

    fun getPokemonInfo(name: String, postAdapter: PostAdapter){
        val call = service.getPokemonInfo(name)

        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if (response.isSuccessful) {
                    response.body()?.let { pokemon ->
                        pokemonInfo.value = pokemon
                    }
                } else{
                    postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                postAdapter.showAlert("A ocurrido un error encontrando al pokemon!")
                call.cancel()
            }

        })
    }
}