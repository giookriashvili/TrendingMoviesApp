package com.example.toptrendingmoviescatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class register : Fragment(R.layout.activity_register) {

    private lateinit var registerMail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var register: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerMail = view.findViewById(R.id.registerMail)
        registerPassword = view.findViewById(R.id.registerPassword)
        repeatPassword = view.findViewById(R.id.repeatPassword)
        register = view.findViewById(R.id.register)


        register.setOnClickListener {
            registerDone()
        }

    }


    private fun registerDone() {

        val mail = registerMail.text.toString()
        val password = registerPassword.text.toString()

        if (mail.isEmpty() || password.isEmpty() || !repeatPassword.text.contains(password)) {
            Toast.makeText(context, "empty", Toast.LENGTH_SHORT).show()
        } else {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.action_register_to_login)
                    } else {
                        Toast.makeText(context,task.exception?.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}