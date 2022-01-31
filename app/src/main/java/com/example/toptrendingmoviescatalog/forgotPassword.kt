package com.example.toptrendingmoviescatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class forgotPassword : Fragment(R.layout.activity_forgot_password) {

    private lateinit var forgotPassMail: EditText
    private lateinit var forgotPassButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        forgotPassButton = view.findViewById(R.id.forgotPassButton)
        forgotPassMail = view.findViewById(R.id.forgotPassMail)

        forgotPassButton.setOnClickListener {
            val email = forgotPassMail.text.toString()
            if (email.isEmpty()){
                Toast.makeText(context, "Please enter email address", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(context, "Email sent successfully to reset your password", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_forgotPassword_to_login)
                        }
                    }
            }
        }

    }
}