package com.example.pokedex.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.PokemonView
import com.example.pokedex.R
import com.example.pokedex.models.Pokemon
import com.example.pokedex.utils.OnPokemonClickListener

class PostAdapter(private var pokeList:ArrayList<Pokemon>, private val onPokemonClickListener: OnPokemonClickListener) : RecyclerView.Adapter<PostViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return PostViewHolder(layoutInflater.inflate(R.layout.item_pokemons,parent,false))
    }

    fun setData(){
        notifyDataSetChanged()
    }

    fun addPokemon(pokemon : Pokemon){
        pokeList.add(pokemon)
        notifyDataSetChanged()
    }

    fun changeList(list:ArrayList<Pokemon>){
        pokeList = list
        notifyDataSetChanged()
    }

    fun openStats(pokemon: Pokemon){
        var pokeStats : ArrayList<Pokemon> = arrayListOf()
        pokeStats.add(pokemon)
        notifyDataSetChanged()
        onPokemonClickListener.onPokemonSearch(pokeStats[0])
    }

    fun showAlert(msg : String){
        onPokemonClickListener.showAlert(msg)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = pokeList[position]
        holder.render(item)
        holder.itemView.setOnClickListener{
            onPokemonClickListener.onPokemonItemCliked(pokeList[position])
        }
    }

    override fun getItemCount(): Int = pokeList.size


}