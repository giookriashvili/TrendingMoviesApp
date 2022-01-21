package com.example.toptrendingmoviescatalog

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity: AppCompatActivity() {

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var login: Button
    private lateinit var forgotPassword: Button
    private lateinit var startRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginEmail = findViewById(R.id.loginEmail)
        loginPassword = findViewById(R.id.loginPassword)
        login = findViewById(R.id.login)
        forgotPassword = findViewById(R.id.forgotPassword)
        startRegister = findViewById(R.id.startRegister)

        login.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(loginEmail.text.toString(),loginPassword.text.toString())
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext,"you are logged in successfully.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this,logged::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(applicationContext,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }

        forgotPassword.setOnClickListener {
            val intent = Intent(this,forgotPassword::class.java)
            startActivity(intent)
        }




        startRegister.setOnClickListener {
            val intent = Intent(this,register::class.java)
            startActivity(intent)
        }
    }
}