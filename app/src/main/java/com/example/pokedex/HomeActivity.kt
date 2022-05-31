package com.example.pokedex

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.adapter.PostAdapter
import com.example.pokedex.databinding.ActivityHomeBinding
import com.example.pokedex.models.PokeInfoViewModel
import com.example.pokedex.models.Pokemon
import com.example.pokedex.utils.OnPokemonClickListener
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class HomeActivity : AppCompatActivity(), OnPokemonClickListener {

    private lateinit var binding: ActivityHomeBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var viewModel: PokeInfoViewModel
    lateinit var adapter: PostAdapter
    lateinit var user : String
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SharedPreferences para almacenar el nombre del usuario
        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString(USER_EXTRA,"")
        user = userName!!
        binding.tvUsername.setText(userName)

        //Inicializamos el RecyclerView
        val recyclerView = binding.recyclerPost
        recyclerView.layoutManager= LinearLayoutManager(this)
        adapter = PostAdapter(pokeList,this)
        recyclerView.adapter = adapter

        //
        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)


        //Agregamos todos los pokemons que tenga el usuaruio al RecyclerView
        db.collection(USERS_COLLECTION).document(userName!!).collection(POKEMONS_COLLECTION).get().addOnCompleteListener {
            pokeList.clear()
            for(document in it.result!!) {
                viewModel.getPokemon(document.get("name") as String, adapter)
            }
            adapter.notifyDataSetChanged()
        }

        // Funciones Principales

        //Atrapar Pokemon
        binding.btCatch.setOnClickListener{
            viewModel.catchPokemon(binding.etCatchPokemon.text.toString(), adapter, userName)
            binding.etCatchPokemon.setText("")
        }

        //Ver estadisticas
        binding.btStats.setOnClickListener {
            viewModel.getPokemonStats(binding.etCatchPokemon.text.toString(), adapter)
            binding.etCatchPokemon.setText("")
        }

        //Buscar un pokemon
        binding.btSearch.setOnClickListener(){
            val search = binding.etSearchPokemon.text.toString()

            //Se consulta si el pokemon esta en los pokemons atrapados por el usuario

            val query = db.collection(USERS_COLLECTION).document(userName).collection(POKEMONS_COLLECTION).whereEqualTo("name",search)
            query.get().addOnCompleteListener(){
                if(it.result!!.size() == 0) {
                    //Si no se encuentra, el recyclerview se muestra vacio
                    var list = arrayListOf<Pokemon>()
                    adapter.changeList(list)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this,"!Aun no tienes este pokemon!",Toast.LENGTH_LONG).show()
                } else {
                    for(document in it.result!!) {
                        //Si encuentra el pokemon, trae los datos completos desde la api
                        viewModel.getPokemonInfo(document.get("name") as String, adapter)
                        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
                            //se muestra solo el pokemon buscado en el recyclerview
                            var list = arrayListOf<Pokemon>()
                            list.add(pokemon)
                            adapter.changeList(list)
                            adapter.notifyDataSetChanged()
                        })
                    }
                }
            }
        }

        //Al borrar la busqueda, aparecen todos los pokemon de nuevo en el recyclerview
        binding.etSearchPokemon.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString()==""){
                    adapter.changeList(pokeList)
                    adapter.setData()
                }
            }
        })

        //Cerrar Sesion

        binding.btLogOut.setOnClickListener {
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(USER_EXTRA,"")
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeActivity
        var pokeList = arrayListOf<Pokemon>()
    }

    override fun onPokemonItemCliked(pokemon: Pokemon) {
        val intent = Intent(this, PokemonView::class.java)
        intent.putExtra("index", pokemon.id)
        intent.putExtra("name", pokemon.name)
        intent.putExtra("type", pokemon.types[0].type.name)
        intent.putExtra("icon", pokemon.sprites.other.official.frontDefault)
        intent.putExtra("height", pokemon.height)
        intent.putExtra("weight", pokemon.weight)
        intent.putExtra("hp", pokemon.stats[0].stat)
        intent.putExtra("attack", pokemon.stats[1].stat)
        intent.putExtra("defense", pokemon.stats[2].stat)
        intent.putExtra("specialAttack", pokemon.stats[3].stat)
        intent.putExtra("specialDefense", pokemon.stats[4].stat)
        intent.putExtra("speed", pokemon.stats[5].stat)
        intent.putExtra("drop",View.VISIBLE)
        intent.putExtra("username",user)
        startActivity(intent)
        finish()
    }

    override fun onPokemonSearch(pokemon: Pokemon) {
        val intent = Intent(this, PokemonView::class.java)
        intent.putExtra("index", pokemon.id)
        intent.putExtra("name", pokemon.name)
        intent.putExtra("type", pokemon.types[0].type.name)
        intent.putExtra("icon", pokemon.sprites.other.official.frontDefault)
        intent.putExtra("height", pokemon.height)
        intent.putExtra("weight", pokemon.weight)
        intent.putExtra("hp", pokemon.stats[0].stat)
        intent.putExtra("attack", pokemon.stats[1].stat)
        intent.putExtra("defense", pokemon.stats[2].stat)
        intent.putExtra("specialAttack", pokemon.stats[3].stat)
        intent.putExtra("specialDefense", pokemon.stats[4].stat)
        intent.putExtra("speed", pokemon.stats[5].stat)
        intent.putExtra("drop",View.INVISIBLE)
        intent.putExtra("username",user)
        startActivity(intent)
        finish()
    }

    override fun showAlert(msg : String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(msg)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}