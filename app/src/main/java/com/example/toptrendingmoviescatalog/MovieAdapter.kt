package com.example.toptrendingmoviescatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.toptrendingmoviescatalog.Models.Movie
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val star: ImageView = view.findViewById(R.id.star)
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindMovie(movie : Movie){
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)

            star.setOnClickListener{
                val dbSavedMovies: DatabaseReference = FirebaseDatabase.getInstance().getReference("Movies")
                val auth = FirebaseAuth.getInstance()
                val Stitle = movie.title.toString()
                val Srelease = movie.release.toString()
                val Sposter = movie.poster.toString()
                val Sid = movie.id.toString()
                val saveMovie = SaveMovie(Stitle, Sposter, Srelease, Sid)
                dbSavedMovies.child(auth.currentUser?.uid!!).child(Sid).setValue(saveMovie)
                val snack = Snackbar.make(itemView,"Saved to favorites",Snackbar.LENGTH_SHORT)
                snack.show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
    }
}