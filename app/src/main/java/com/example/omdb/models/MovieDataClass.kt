package com.example.omdb.models

import com.google.gson.annotations.SerializedName

data class MovieDataClass(
    @SerializedName("Title") val title: Any,
    @SerializedName("Year") val year: Any,
    @SerializedName("Rated") val rated: Any,
    @SerializedName("Released") val released: Any,
    @SerializedName("Runtime") val runtime: Any,
    @SerializedName("Genre") val genre: Any,
    @SerializedName("Director") val director: Any,
    @SerializedName("Writer") val writer: Any,
    @SerializedName("Actors") val actors: Any,
    @SerializedName("Plot") val plot: Any,
    @SerializedName("Language") val language: Any,
    @SerializedName("Country") val country: Any,
    @SerializedName("Awards") val awards: Any,
    @SerializedName("Poster") val poster: Any,
    @SerializedName("Ratings") val ratings: List<Ratings>,
    @SerializedName("Metascore") val metascore: Any,
    @SerializedName("imdbRating") val imdbRating: Any,
    @SerializedName("imdbVotes") val imdbVotes: Any,
    @SerializedName("imdbID") val imdbID: Any,
    @SerializedName("Type") val type: Any,
    @SerializedName("DVD") val dVD: Any,
    @SerializedName("BoxOffice") val boxOffice: Any,
    @SerializedName("Production") val production: Any,
    @SerializedName("Website") val website: Any,
    @SerializedName("Response") val response: Boolean
)
