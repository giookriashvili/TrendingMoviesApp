package com.example.toptrendingmoviescatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.toptrendingmoviescatalog.Models.Movie
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FavoriteAdapter(private var list: List<FavoriteMovies>): RecyclerView.Adapter<FavoriteAdapter.SavedMoviesViewHolder>() {


    class SavedMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.movie_poster)
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val releaseDate: TextView = itemView.findViewById(R.id.movie_release_date)
        val remove: Button = itemView.findViewById(R.id.remove)
        val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

        fun setData(movie: FavoriteMovies) {
            val movieUrl = IMAGE_BASE.plus(movie.poster)

            Glide.with(itemView.context)
                .load(movieUrl)
                .centerCrop()
                .into(imageView)

            remove.setOnClickListener {
                val id= movie.id.toString()
                val favoriteMovies: DatabaseReference =
                    FirebaseDatabase.getInstance().getReference("Movies")
                val auth = FirebaseAuth.getInstance()
                favoriteMovies.child(auth.currentUser?.uid!!).child(id).removeValue()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favorite_movies_item, parent,false)
        return SavedMoviesViewHolder(view)

    }

    override fun onBindViewHolder(holder: SavedMoviesViewHolder, position: Int) {
        val savedMovie = list[position]
        holder.setData(savedMovie)
        holder.title.text = savedMovie.title
        holder.releaseDate.text = savedMovie.release


    }

    override fun getItemCount() = list.size


}