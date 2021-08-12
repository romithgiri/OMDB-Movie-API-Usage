package com.example.omdb.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object RetroFitApiFunction {
    private const val BASE_URL = "http://www.omdbapi.com/?apikey=306a33ca"

    fun apiCallMovieList(key: String): Response {
        val url = "$BASE_URL&t=$key"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute()
    }

    fun apiCallMovieDetail(id: String): Response {
        val url = "$BASE_URL&i=$id"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute()
    }
}
