package com.example.toptrendingmoviescatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class forgotPassword : AppCompatActivity() {

    private lateinit var forgotPassMail: EditText
    private lateinit var forgotPassButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        forgotPassButton = findViewById(R.id.forgotPassButton)
        forgotPassMail = findViewById(R.id.forgotPassMail)

        forgotPassButton.setOnClickListener {
            val email = forgotPassMail.text.toString()
            if (email.isEmpty()){
                Toast.makeText(this, "Please enter email address", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Email sent successfully to reset your password", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
            }
        }

    }
}