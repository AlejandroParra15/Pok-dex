package com.example.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.adapter.PostAdapter
import com.example.pokedex.models.Pokemon

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerPost)
        recyclerView.layoutManager= LinearLayoutManager(this.applicationContext)
        recyclerView.adapter= PostAdapter(pokeList)
        pokeList.add(Pokemon("#25","Ditto","Normal","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png",17,905,75,80,50,50,50,38))
        pokeList.add(Pokemon("#25","Charmander","Fire","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",17,905,75,80,50,50,50,38))
        pokeList.add(Pokemon("#25","Charizard","Fire","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png",17,905,75,80,50,50,50,38))
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeActivity

        var pokeList = arrayListOf<Pokemon>()

    }
}