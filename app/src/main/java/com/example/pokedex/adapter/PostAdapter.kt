package com.example.pokedex.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.PokemonView
import com.example.pokedex.R
import com.example.pokedex.models.Pokemon
import com.example.pokedex.utils.OnPokemonClickListener

class PostAdapter(private val pokeList:List<Pokemon>,private val onPokemonClickListener: OnPokemonClickListener) : RecyclerView.Adapter<PostViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return PostViewHolder(layoutInflater.inflate(R.layout.item_pokemons,parent,false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = pokeList[position]
        holder.render(item)
        holder.itemView.setOnClickListener{
            onPokemonClickListener.onPokemonItemCliked(position)
        }
    }

    override fun getItemCount(): Int = pokeList.size


}