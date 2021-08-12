package com.example.omdb.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.omdb.models.MovieDataClass
import com.example.omdb.models.Ratings
import com.example.omdb.utils.RetroFitApiFunction
import com.google.gson.Gson
import kotlinx.coroutines.*

class MovieListViewModel : ViewModel {
    var title = ""
    var year = ""
    var rated = ""
    var released = ""
    var runTime = ""
    var genre = ""
    var director = ""
    var writer = ""
    var actor = ""
    var plot = ""
    var language = ""
    var country = ""
    var awards = ""
    var poster = ""
    var ratings: List<Ratings>? = null
    var metascore = ""
    var imdbRating = ""
    var imdbVotes = ""
    var imdbID = ""
    var type = ""
    var response: Boolean = false

    constructor() : super()

    constructor(list: MovieDataClass) : super() {
        this.title = list.title.toString()
        this.year = list.year.toString()
        this.rated = list.rated.toString()
        this.released = list.released.toString()
        this.runTime = list.runtime.toString()
        this.genre = list.genre.toString()
        this.director = list.director.toString()
        this.writer = list.writer.toString()
        this.actor = list.actors.toString()
        this.plot = list.plot.toString()
        this.language = list.language.toString()
        this.country = list.country.toString()
        this.awards = list.awards.toString()
        this.poster = list.poster.toString()
        this.ratings = list.ratings
        this.metascore = list.metascore.toString()
        this.imdbRating = list.imdbRating.toString()
        this.imdbVotes = list.imdbVotes.toString()
        this.imdbID = list.imdbID.toString()
        this.type = list.type.toString()
        this.response = list.response
    }

    var arrayListMutableLiveData = MutableLiveData<ArrayList<MovieDataClass>>()
    var arraylist = ArrayList<MovieDataClass>()

    fun getMovieSearchList(title: String): MutableLiveData<ArrayList<MovieDataClass>> {
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetroFitApiFunction.apiCallMovieList(title)
            if (response.isSuccessful) {
                var body = response.body!!.string()
                var obj = Gson().fromJson(body, MovieDataClass::class.java)
                arraylist.add(obj)
                println("Task 5:$arraylist")
                arrayListMutableLiveData.postValue(arraylist)
            } else {
                Log.e("Error", "Error: in api call ->>>>>> : ${response.message}")
            }
        }
        return arrayListMutableLiveData
    }

    fun getMovieDetail(id: String): MutableLiveData<ArrayList<MovieDataClass>> {
        GlobalScope.launch(Dispatchers.IO) {
            val response = RetroFitApiFunction.apiCallMovieDetail(id)
            if (response.isSuccessful) {
                var body = response.body!!.string()
                var obj = Gson().fromJson(body, MovieDataClass::class.java)
                arraylist.add(obj)
                arrayListMutableLiveData.postValue(arraylist)
            } else {
                Log.e("Error", "Error: in api call ->>>>>> : ${response.message}")
            }
        }
        return arrayListMutableLiveData
    }
}