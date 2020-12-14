package com.example.chuknorrisjokes.model

import com.google.gson.annotations.SerializedName


data class Joke(
    @SerializedName("joke")
    var joke: String? = null
)