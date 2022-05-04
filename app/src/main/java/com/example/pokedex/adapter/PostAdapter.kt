package com.example.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.models.Pokemon

class PostAdapter(private val pokeList:List<Pokemon>) : RecyclerView.Adapter<PostViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return PostViewHolder(layoutInflater.inflate(R.layout.item_pokemons,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = pokeList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = pokeList.size

}