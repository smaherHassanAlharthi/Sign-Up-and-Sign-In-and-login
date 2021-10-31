package com.example.signupandsignin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.signupandsignin.database.DatabaseHelper

class Login : AppCompatActivity() {
    val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getSupportActionBar()?.hide()

        val username = findViewById<EditText>(R.id.etUsernameSI)
        val password = findViewById<EditText>(R.id.etPassSI)
        val signup = findViewById<TextView>(R.id.tvSignUp)
        val login = findViewById<Button>(R.id.btLogin)
        val facebook = findViewById<Button>(R.id.tbFbSI)

        login.setOnClickListener{
            if(username.text.isNotBlank()&&password.text.isNotBlank()) {
                val user= databaseHelper.checkLogin(username.text.toString(), password.text.toString())
                val intent = Intent(this, Profile::class.java)
                intent.putExtra("user", user)
                startActivity(intent)

            }
            else
            Toast.makeText(applicationContext, "Can not be empty ", Toast.LENGTH_SHORT).show()
        }
        facebook.setOnClickListener{
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
            startActivity(browserIntent)
        }
        signup.setOnClickListener{
            startActivity(Intent(this, SignUp::class.java))
        }
    }
}