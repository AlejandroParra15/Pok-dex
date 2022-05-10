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
        index.text=postModel.index
        name.text=postModel.name
        type.text=postModel.type
        Glide.with(icon.context).load(postModel.icon).into(icon)

        var typeColor = 0

        when (type.text) {
            "Normal" -> typeColor = banner.resources.getColor(R.color.TypeNormal)
            "Fire" -> typeColor = banner.resources.getColor(R.color.TypeFire)
            "softfire" -> typeColor = banner.resources.getColor(R.color.TypeSoftFire)
            "Water" -> typeColor = banner.resources.getColor(R.color.TypeWater)
            "Electric" -> typeColor = banner.resources.getColor(R.color.TypeElectric)
            "Grass" -> typeColor =banner.resources.getColor(R.color.TypeGrass)
            "Ice" -> typeColor = banner.resources.getColor(R.color.TypeIce)
            "Fighting" -> typeColor = banner.resources.getColor(R.color.TypeFighting)
            "Poison" -> typeColor = banner.resources.getColor(R.color.TypePoison)
            "Ground" -> typeColor = banner.resources.getColor(R.color.TypeGround)
            "Flying" -> typeColor = banner.resources.getColor(R.color.TypeFlying)
            "Psychic" -> typeColor = banner.resources.getColor(R.color.TypePsychic)
            "Bug" -> typeColor = banner.resources.getColor(R.color.TypeBug)
            "Rock" -> typeColor = banner.resources.getColor(R.color.TypeRock)
            "Ghost" -> typeColor = banner.resources.getColor(R.color.TypeGhost)
            "Dragon" -> typeColor = banner.resources.getColor(R.color.TypeDragon)
            "Dark" -> typeColor = banner.resources.getColor(R.color.TypeDark)
            "Steel" -> typeColor = banner.resources.getColor(R.color.TypeSteel)
            "Fairy" -> typeColor = banner.resources.getColor(R.color.TypeFairy)
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

