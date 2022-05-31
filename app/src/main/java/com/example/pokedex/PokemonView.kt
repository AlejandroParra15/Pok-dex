package com.example.pokedex

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.pokedex.databinding.ActivityPokemonViewBinding
import com.example.pokedex.models.Pokemon
import com.google.firebase.firestore.FirebaseFirestore


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
        binding.btSoltar.visibility = intent.getIntExtra("drop",View.INVISIBLE)
        val userName = intent.getStringExtra("username")

        binding.btSoltar.setOnClickListener {
            var db = FirebaseFirestore.getInstance()
            val query = db.collection(USERS_COLLECTION).document(userName!!).collection(POKEMONS_COLLECTION).whereEqualTo("name",binding.tvNameView.text.toString())
            query.get().addOnCompleteListener(){
                for(document in it.result!!) {
                    db.collection(USERS_COLLECTION).document(userName!!).collection(POKEMONS_COLLECTION).document(document.id).delete().addOnSuccessListener {
                        Toast.makeText(this,"Este pokemon ahora es libre!",Toast.LENGTH_LONG).show()
                    }
                }
            }
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        var typeColor = 0

        when (type) {
            "normal" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeNormal)
            "fire" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFire)
            "softfire" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeSoftFire)
            "water" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeWater)
            "electric" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeElectric)
            "grass" -> typeColor =binding.banner.resources.getColor(com.example.pokedex.R.color.TypeGrass)
            "ice" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeIce)
            "fighting" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFighting)
            "poison" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypePoison)
            "ground" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeGround)
            "flying" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFlying)
            "psychic" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypePsychic)
            "bug" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeBug)
            "rock" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeRock)
            "ghost" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeGhost)
            "dragon" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeDragon)
            "dark" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeDark)
            "steel" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeSteel)
            "fairy" -> typeColor = binding.banner.resources.getColor(com.example.pokedex.R.color.TypeFairy)
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
        binding.btSoltar.background = gd
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

}

