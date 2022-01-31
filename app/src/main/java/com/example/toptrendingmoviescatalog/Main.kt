package com.example.toptrendingmoviescatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class Main : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        supportActionBar?.hide()



        navView = findViewById(R.id.bottomNavigationView)

        val controller = findNavController(R.id.fragment_container)

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.movies,
                R.id.favorites,
            )
        )
        setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)


        controller.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movies -> showBottomNav()
                R.id.favorites -> showBottomNav()
                else -> hideBottomNav()
            }
        }



    }

    private fun showBottomNav() {
        navView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        navView.visibility = View.GONE

    }
}