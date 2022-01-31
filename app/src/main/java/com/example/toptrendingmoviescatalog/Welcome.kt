package com.example.toptrendingmoviescatalog

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class Welcome: Fragment(R.layout.welcome) {

    private lateinit var startLogin: Button
    private lateinit var startRegister: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //if(FirebaseAuth.getInstance().currentUser != null){
        //   findNavController().navigate(R.id.action_welcome_to_movies)
       // }

        super.onViewCreated(view, savedInstanceState)

        startLogin = view.findViewById(R.id.startLogin)
        startRegister = view.findViewById(R.id.startRegister)

        startLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_login)
        }

        startRegister.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_register)
        }

    }
}