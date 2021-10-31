package com.example.signupandsignin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.signupandsignin.database.DatabaseHelper
import com.example.signupandsignin.database.User

class SignUp : AppCompatActivity() {
    val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        getSupportActionBar()?.hide()

        val fb = findViewById<Button>(R.id.tbFb)
        val signUp = findViewById<Button>(R.id.btSignUp)
        val mobile = findViewById<EditText>(R.id.etMobile)
        val name = findViewById<EditText>(R.id.etName)
        val username = findViewById<EditText>(R.id.etUser)
        val password = findViewById<EditText>(R.id.etPass)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)


        fb.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/"))
            startActivity(browserIntent)
        }
        signUp.setOnClickListener {
            if (mobile.text.isNotBlank() && name.text.isNotBlank() && username.text.isNotBlank() && password.text.isNotBlank()) {

                if (checkBox.isChecked) {
                    var user = User(mobile.text.toString(),
                        name.text.toString(),
                        username.text.toString(),
                        password.text.toString())
                    databaseHelper.saveData(user)
                    val intent = Intent(this, Profile::class.java)
                    intent.putExtra("user", user)
                    startActivity(intent)
                } else
                    Toast.makeText(
                        this,
                        "You must agree to terms and conditions",
                        Toast.LENGTH_SHORT
                    ).show()

            } else
                Toast.makeText(
                    this,
                    "You must fill all the required information",
                    Toast.LENGTH_SHORT
                ).show()

        }
    }
    /*fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }*/
}
