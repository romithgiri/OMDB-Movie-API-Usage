package com.example.omdb.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.example.omdb.R
import com.example.omdb.viewmodels.MovieListViewModel
import com.squareup.picasso.Picasso

class MovieDetail : AppCompatActivity() {
    lateinit var movieDetailScreenClose: ImageView
    lateinit var movieBanner: ImageView
    lateinit var movieName: TextView
    lateinit var movieReleasedDate: TextView
    lateinit var movieCast: TextView
    lateinit var moviePlot: TextView
    lateinit var noData: TextView
    lateinit var loader: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        var extras = intent.extras
        var key = extras?.getString("id").toString()

        movieDetailScreenClose = findViewById(R.id.iv_movie_detail_close)
        movieBanner = findViewById(R.id.iv_movie_detail_banner)
        movieName = findViewById(R.id.tv_movie_detail_name)
        movieCast = findViewById(R.id.tv_movie_detail_cast)
        movieReleasedDate = findViewById(R.id.tv_movie_detail_release_date)
        moviePlot = findViewById(R.id.tv_movie_detail_plot)
        loader = findViewById(R.id.loader_detail)
        noData = findViewById(R.id.tv_no_data_detail_found)

        var movieListViewModel: MovieListViewModel = ViewModelProviders.of(this).get(
            MovieListViewModel::class.java
        )

        movieListViewModel.getMovieDetail(key).observe(this, { movieListViewModel ->
            if (movieListViewModel.isNullOrEmpty()) {
                noData.visibility = View.VISIBLE
                loader.visibility = View.INVISIBLE
                movieBanner.visibility = View.INVISIBLE
                movieName.visibility = View.INVISIBLE
                movieReleasedDate.visibility = View.INVISIBLE
                movieCast.visibility = View.INVISIBLE
                moviePlot.visibility = View.INVISIBLE
            } else {
                noData.visibility = View.INVISIBLE
                loader.visibility = View.INVISIBLE
                Picasso.with(this).load(movieListViewModel[0].poster.toString())
                    .placeholder(R.drawable.broken_image).into(movieBanner)
                movieName.text = movieListViewModel[0].title.toString()
                movieReleasedDate.text = movieListViewModel[0].released.toString()
                movieCast.text = "Cast: ${movieListViewModel[0].actors}"
                moviePlot.text = "Plot: ${movieListViewModel[0].plot}"

                /*loader.visibility = View.VISIBLE
                movieBanner.visibility = View.VISIBLE
                movieName.visibility = View.VISIBLE
                movieReleasedDate.visibility = View.VISIBLE
                movieCast.visibility = View.VISIBLE
                moviePlot.visibility = View.VISIBLE*/
            }
        })

        movieDetailScreenClose.setOnClickListener {
            finish()
        }


    }
}