package com.example.signupandsignin.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DatabaseHelper(val context: Context): SQLiteOpenHelper(context,"Users.db",null,1) {
    var sqLiteDatabase: SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("CREATE TABLE users (_id INTEGER PRIMARY KEY autoincrement,EmailPhone text, Name text,UserName TEXT NOT NULL UNIQUE,Password text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun saveData(user: User): Long {

        val cv = ContentValues()
        cv.put("EmailPhone", user.email)
        cv.put("Name", user.name)
        cv.put("UserName", user.username)
        cv.put("Password", user.password)
        //since username is unique
        var status = sqLiteDatabase.insert("users", null, cv)
        //check status if -1 means not added because same username
        if(status>=0)
        Toast.makeText(context,"Added Successfully! $status",Toast.LENGTH_SHORT).show()
        else
        Toast.makeText(context,"Username is already used!!",Toast.LENGTH_SHORT).show()
        return status
    }

    fun checkLogin(userName: String,password:String): User? {
        var user: User? =null
        val c: Cursor = sqLiteDatabase.rawQuery("SELECT * FROM users where users.UserName='$userName' and users.Password='$password'",null)
        if (c.count < 1) // UserName Not Exist
        {
            Toast.makeText(context,"username or password is not correct",Toast.LENGTH_SHORT).show()
        }else {
            c.moveToFirst()
           user = User(c.getString(1),c.getString(2),c.getString(3),c.getString(4))
        }
         return user
    }

    fun retrieveData(): ArrayList<User> {
        val users=ArrayList<User>()
        var c=sqLiteDatabase.rawQuery("SELECT * FROM users",null)

        //iterate through cursor and save to array list
        if (c != null) {
            while (c.moveToNext())
            {
                var user = User(c.getString(1),c.getString(2),c.getString(3)
                ,c.getString(4))

                users.add(user)
            }
        }
        return users
    }

}

