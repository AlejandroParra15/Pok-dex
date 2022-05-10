package com.example.pokedex

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.adapter.PostAdapter
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.models.Pokemon
import com.example.pokedex.utils.OnPokemonClickListener
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity(), OnPokemonClickListener {

    private lateinit var binding: ActivityHomeBinding
    lateinit var sharedPreferences: SharedPreferences
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString(USER_EXTRA,"")
        binding.tvUsername.setText(userName)

        //
        val recyclerView = binding.recyclerPost
        recyclerView.layoutManager= LinearLayoutManager(this.applicationContext)
        var adapter = PostAdapter(pokeList,this)
        recyclerView.adapter = adapter

        //
        //db.collection(USERS_COLLECTION).document(user.id).set(user)
        pokeList.add(Pokemon("#135","Ditto","Normal","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/132.png",52,400,80,45,25,10,80,58))
        pokeList.add(Pokemon("#25","Charmander","Fire","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png",98,905,150,80,50,50,50,38))
        pokeList.add(Pokemon("#18","Charizard","Dragon","https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png",26,250,60,90,70,100,30,28))

    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeActivity
        var pokeList = arrayListOf<Pokemon>()
    }

    override fun onPokemonItemCliked(position: Int) {
        val intent = Intent(this, PokemonView::class.java)
        intent.putExtra("index", pokeList[position].index)
        intent.putExtra("name", pokeList[position].name)
        intent.putExtra("type", pokeList[position].type)
        intent.putExtra("icon", pokeList[position].icon)
        intent.putExtra("height", pokeList[position].height)
        intent.putExtra("weight", pokeList[position].weight)

        intent.putExtra("hp", pokeList[position].hp)
        intent.putExtra("attack", pokeList[position].attack)
        intent.putExtra("defense", pokeList[position].defense)
        intent.putExtra("specialAttack", pokeList[position].specialAttack)
        intent.putExtra("specialDefense", pokeList[position].specialDefense)
        intent.putExtra("speed", pokeList[position].speed)
        startActivity(intent)
    }
}