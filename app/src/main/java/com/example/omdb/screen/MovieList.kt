package com.example.omdb.screen

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.omdb.R
import com.example.omdb.adapter.RecyclerAdapter
import com.example.omdb.viewmodels.MovieListViewModel


class MovieList : AppCompatActivity() {
    private lateinit var movieListRecyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var noData: TextView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var backArrow: ImageView
    private lateinit var toolbarTitle: TextView
    private lateinit var toolbarSubTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        var extras = intent.extras
        var key = extras?.getString("movieName").toString()

        loader = findViewById(R.id.loader)
        noData = findViewById(R.id.tv_no_data_found)
        backArrow = findViewById(R.id.iv_back_button_movie_list)
        toolbarSubTitle = findViewById(R.id.tv_movie_list_toolbar_sub_title)
        movieListRecyclerView = findViewById(R.id.rv_movie_list)

        movieListRecyclerView.visibility = View.INVISIBLE
        loader.visibility = View.VISIBLE
        toolbarSubTitle.text = key

        var movieListViewModel: MovieListViewModel = ViewModelProviders.of(this).get(
            MovieListViewModel::class.java
        )

        movieListViewModel.getMovieSearchList(key).observe(this, { movieListViewModel ->
                if (movieListViewModel.isNullOrEmpty()) {
                    loader.visibility = View.INVISIBLE
                    movieListRecyclerView.visibility = View.INVISIBLE
                    noData.visibility = View.VISIBLE
                } else {
                    movieListRecyclerView.visibility = View.VISIBLE
                    loader.visibility = View.INVISIBLE
                    recyclerAdapter = RecyclerAdapter(this, movieListViewModel)
                    layoutManager = GridLayoutManager(this, 2)
                    movieListRecyclerView.layoutManager = layoutManager
                    movieListRecyclerView.adapter = recyclerAdapter
                }
            }
        )

        backArrow.setOnClickListener {
            finish()
        }
    }

}