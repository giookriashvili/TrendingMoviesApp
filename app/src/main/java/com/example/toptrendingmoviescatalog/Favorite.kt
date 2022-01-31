package com.example.toptrendingmoviescatalog

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Favorite:Fragment(R.layout.favorite) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var image: ImageView
    private lateinit var text: TextView
    private lateinit var remove: Button
    private lateinit var favoriteAdapter: FavoriteAdapter


    private lateinit var newsArrayList: ArrayList<FavoriteMovies>

    private val auth = FirebaseAuth.getInstance()
    private val dbSavedNews = FirebaseDatabase.getInstance().getReference("Movies")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)


        newsArrayList = arrayListOf<FavoriteMovies>()
        getSavedNews()
        recyclerView.setHasFixedSize(true)
    }

    private fun getSavedNews() {

        dbSavedNews.child(auth.currentUser?.uid!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for(savedNewsSnapshot in snapshot.children){
                        val savedNews = savedNewsSnapshot.getValue(FavoriteMovies::class.java)
                        newsArrayList.add(savedNews!!)

                    }
                    recyclerView.adapter = FavoriteAdapter(newsArrayList)
                    recyclerView.layoutManager = LinearLayoutManager(context)


                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


}