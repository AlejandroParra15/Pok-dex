package com.example.pokedex

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.*
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ActivityPokemonViewBinding


class PokemonView : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonViewBinding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var type = intent.getStringExtra("type")
        binding.tvIndexView.text = intent.getStringExtra("index")
        binding.tvNameView.text = intent.getStringExtra("name")
        binding.btTypeView.text = type
        Glide.with(binding.ivPokeView.context).load(intent.getStringExtra("icon")).into(binding.ivPokeView)
        binding.tvHeight.text = intent.getIntExtra("height",0).toString()+" m"
        binding.tvWeight.text = intent.getIntExtra("weight",0).toString()+" kg"

        binding.pbHP.progress = intent.getIntExtra("hp",0)
        binding.pbATK.progress = intent.getIntExtra("attack",0)
        binding.pbDEF.progress = intent.getIntExtra("defense",0)
        binding.pbSpAtk.progress = intent.getIntExtra("specialAttack",0)
        binding.pbSpDef.progress = intent.getIntExtra("specialDefense",0)
        binding.pbSpd.progress = intent.getIntExtra("speed",0)

        var typeColor = 0

        when (type) {
            "Normal" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeNormal)
            "Fire" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFire)
            "softfire" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeSoftFire)
            "Water" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeWater)
            "Electric" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeElectric)
            "Grass" -> typeColor =binding.banner.resources.getColor(com.example.pokedex.R.color.TypeGrass)
            "Ice" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeIce)
            "Fighting" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFighting)
            "Poison" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypePoison)
            "Ground" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeGround)
            "Flying" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFlying)
            "Psychic" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypePsychic)
            "Bug" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeBug)
            "Rock" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeRock)
            "Ghost" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeGhost)
            "Dragon" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeDragon)
            "Dark" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeDark)
            "Steel" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeSteel)
            "Fairy" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFairy)
        }

        val strokeWidth = 5 // 5px not dp
        val roundRadius = 30 // 15px not dp
        val strokeColor: Int = Color.parseColor("#000000")
        val fillColor: Int = typeColor

        val gd = GradientDrawable()
        gd.setColor(fillColor)
        gd.cornerRadius = roundRadius.toFloat()
        gd.setStroke(strokeWidth, strokeColor)

        binding.banner.setBackgroundColor(typeColor)
        binding.btTypeView.background = gd
    }
}

