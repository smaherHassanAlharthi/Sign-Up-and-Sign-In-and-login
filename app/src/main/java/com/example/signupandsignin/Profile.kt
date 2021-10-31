package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.signupandsignin.database.User

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

          val user = intent.getSerializableExtra("user") as User?
        findViewById<TextView>(R.id.tvProName).setText(user!!.name)
        findViewById<TextView>(R.id.tvUserName).setText(user.username)
        findViewById<TextView>(R.id.tvEmailPro).setText(user.email)

        val signOut=findViewById<Button>(R.id.btSignOut)
        signOut.setOnClickListener{
            startActivity(Intent(this, StartActivity::class.java))
        }
    }
}