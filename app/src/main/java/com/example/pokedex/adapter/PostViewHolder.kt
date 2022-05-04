package com.example.pokedex.adapter


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.example.pokedex.models.Pokemon
import com.example.pokedex.R

class PostViewHolder(view:View) :RecyclerView.ViewHolder(view){

    val index=view.findViewById<TextView>(R.id.lbIndex)
    val name=view.findViewById<TextView>(R.id.lbName)
    val type=view.findViewById<TextView>(R.id.btType)
    val icon=view.findViewById<ImageView>(R.id.ivIcon)


    fun render(postModel:Pokemon){
        index.text=postModel.index
        name.text=postModel.name
        type.text=postModel.type
        Glide.with(icon.context).load(postModel.icon).into(icon)
    }

}

