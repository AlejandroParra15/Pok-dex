package com.example.pokedex.adapter


import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.models.Pokemon


class PostViewHolder(view:View) :RecyclerView.ViewHolder(view){

    val index=view.findViewById<TextView>(R.id.lbIndex)
    val name=view.findViewById<TextView>(R.id.lbName)
    val type=view.findViewById<TextView>(R.id.btType)
    val icon=view.findViewById<ImageView>(R.id.ivIcon)
    val banner=view.findViewById<ConstraintLayout>(R.id.item_banner)

    @SuppressLint("ResourceAsColor")
    fun render(postModel:Pokemon){
        index.text=postModel.id
        name.text=postModel.name
        type.text= postModel.types[0].type.name
        Glide.with(icon.context).load(postModel.sprites.other.official.frontDefault).into(icon)

        var typeColor = 0

        when (type.text) {
            "normal" -> typeColor = banner.resources.getColor(R.color.TypeNormal)
            "fire" -> typeColor = banner.resources.getColor(R.color.TypeFire)
            "softfire" -> typeColor = banner.resources.getColor(R.color.TypeSoftFire)
            "water" -> typeColor = banner.resources.getColor(R.color.TypeWater)
            "electric" -> typeColor = banner.resources.getColor(R.color.TypeElectric)
            "grass" -> typeColor =banner.resources.getColor(R.color.TypeGrass)
            "ice" -> typeColor = banner.resources.getColor(R.color.TypeIce)
            "fighting" -> typeColor = banner.resources.getColor(R.color.TypeFighting)
            "poison" -> typeColor = banner.resources.getColor(R.color.TypePoison)
            "ground" -> typeColor = banner.resources.getColor(R.color.TypeGround)
            "flying" -> typeColor = banner.resources.getColor(R.color.TypeFlying)
            "psychic" -> typeColor = banner.resources.getColor(R.color.TypePsychic)
            "bug" -> typeColor = banner.resources.getColor(R.color.TypeBug)
            "rock" -> typeColor = banner.resources.getColor(R.color.TypeRock)
            "ghost" -> typeColor = banner.resources.getColor(R.color.TypeGhost)
            "dragon" -> typeColor = banner.resources.getColor(R.color.TypeDragon)
            "dark" -> typeColor = banner.resources.getColor(R.color.TypeDark)
            "steel" -> typeColor = banner.resources.getColor(R.color.TypeSteel)
            "fairy" -> typeColor = banner.resources.getColor(R.color.TypeFairy)
        }

        val strokeWidth = 5 // 5px not dp
        val roundRadius = 30 // 15px not dp
        val strokeColor: Int = Color.parseColor("#000000")
        val fillColor: Int = typeColor

        val gd = GradientDrawable()
        gd.setColor(fillColor)
        gd.cornerRadius = roundRadius.toFloat()
        gd.setStroke(strokeWidth, strokeColor)

        banner.setBackgroundColor(typeColor)
        type.background = gd
    }

}

