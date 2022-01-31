package com.example.toptrendingmoviescatalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toptrendingmoviescatalog.Models.Movie
import com.example.toptrendingmoviescatalog.Models.MovieResponse
import com.example.toptrendingmoviescatalog.services.MovieApiInterface
import com.example.toptrendingmoviescatalog.services.MovieApiService
import kotlinx.android.synthetic.main.movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movies : Fragment(R.layout.movies) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_movies_list.layoutManager = LinearLayoutManager(context)
        rv_movies_list.setHasFixedSize(true)
        getMovieData { movies: List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }
    }

    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }

        })
    }
}