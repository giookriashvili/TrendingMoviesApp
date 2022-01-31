package com.example.toptrendingmoviescatalog

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class Login: Fragment(R.layout.activity_main) {

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var login: Button
    private lateinit var forgotPassword: Button
    private lateinit var startRegister: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginEmail = view.findViewById(R.id.loginEmail)
        loginPassword = view.findViewById(R.id.loginPassword)
        login = view.findViewById(R.id.login)
        forgotPassword = view.findViewById(R.id.forgotPassword)
        startRegister = view.findViewById(R.id.startRegister)

        login.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(loginEmail.text.toString(),loginPassword.text.toString())
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(context,"you are Movies in successfully.", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_login_to_logged)
                    }else{
                        Toast.makeText(context,task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
        }

        forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_forgotPassword)
        }




        startRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
    }
}