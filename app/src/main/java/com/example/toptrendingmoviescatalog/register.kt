package com.example.toptrendingmoviescatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {

    private lateinit var registerMail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var register: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        register.setOnClickListener {
            registerDone()
        }

    }

    private fun init(){
        registerMail = findViewById(R.id.registerMail)
        registerPassword = findViewById(R.id.registerPassword)
        repeatPassword = findViewById(R.id.repeatPassword)
        register = findViewById(R.id.register)
    }

    private fun registerDone() {

        val mail = registerMail.text.toString()
        val password = registerPassword.text.toString()

        if (mail.isEmpty() || password.isEmpty() || !repeatPassword.text.contains(password)) {
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show()
        } else {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("email",mail)
                        intent.putExtra("password",password)
                        setResult(RESULT_OK,intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext,task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}