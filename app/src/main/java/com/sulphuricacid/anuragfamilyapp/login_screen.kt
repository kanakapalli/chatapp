package com.sulphuricacid.anuragfamilyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.login_screen.*

class login_screen : AppCompatActivity() {

    lateinit var email: EditText
    lateinit var password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedprefs= SharedPref(this)
        val username = sharedprefs.getuserdetails()
        val loginpassword = sharedprefs.PREFRENCE_Password
        Log.d("usernmae","${username}")
        Log.d("paswrd", loginpassword)


//        if(username === "UserName" || loginpassword === "paswrd"){
//            val intent = Intent(this, mainscreen::class.java)
//            startActivity(intent)
//        }else{
        setContentView(R.layout.login_screen)
        supportActionBar!!.hide()

//        declartions
        val loginbutton: Button = findViewById(R.id.login_button)
        val signButton: TextView = findViewById(R.id.sign_button)
        email = findViewById(R.id.usernameET)
        password = findViewById(R.id.passwordET)


        loginbutton.setOnClickListener {
            if(email.text.toString().isEmpty() || password.text.toString().isEmpty() ){
                Toast.makeText(this, "fill the email/password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(this, "suck's us", Toast.LENGTH_SHORT).show()
//            setContentView(R.layout.activity_mainscreen)
            val intent = Intent(this, mainscreen::class.java)
            startActivity(intent)

        }
//            sign
        signButton.setOnClickListener {

            val intent = Intent(
                this,
                signIn::class.java
            )
            startActivity(intent)

        }
//        forgotpassword
        forgotpwTV.setOnClickListener {
            val intent = Intent(this, forgotpassword::class.java)
            startActivity(intent)
        }
    }


//    }
}

