package com.example.pokedex

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokedex.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var db = FirebaseFirestore.getInstance()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString(USER_EXTRA,"")
        if(userName != ""){
            goToHome()
        }

        binding.loginBtn.setOnClickListener {
            val username = binding.usernameTxt.text.toString()
            if(username.isNotEmpty()) {
                loginOrSignup(username)
            } else {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginOrSignup(username: String) {
        val query = db.collection(USERS_COLLECTION).whereEqualTo(USERNAME_FIELD,username)
        query.get().addOnCompleteListener {
            if(it.result!!.size() == 0) {
                val user = User(UUID.randomUUID().toString(), username)
                db.collection(USERS_COLLECTION).document(user.username).set(user)
                val editor : SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(USER_EXTRA,username)
                editor.apply()
                goToHome()
            } else {
                lateinit var existingUser: User
                for(document in it.result!!) {
                    existingUser = document.toObject(User::class.java)
                    goToHome()
                    break
                }
            }
        }
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}