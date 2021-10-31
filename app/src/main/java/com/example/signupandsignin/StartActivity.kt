package com.example.signupandsignin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        getSupportActionBar()?.hide()


        val create = findViewById<Button>(R.id.btCreate)
        val login = findViewById<TextView>(R.id.tvLogin)


        login.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }

        create.setOnClickListener{
            startActivity(Intent(this, SignUp::class.java))
        }
    }
}