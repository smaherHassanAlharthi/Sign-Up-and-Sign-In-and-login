package com.example.signupandsignin.database

import java.io.Serializable

data class User( val email:String, val name:String,val username: String,val password:String):Serializable {
}