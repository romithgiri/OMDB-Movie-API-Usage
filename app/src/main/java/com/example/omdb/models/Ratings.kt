package com.example.omdb.models

import com.google.gson.annotations.SerializedName

data class Ratings(
    @SerializedName("Source") val source: Any,
    @SerializedName("Value") val value: Any
)