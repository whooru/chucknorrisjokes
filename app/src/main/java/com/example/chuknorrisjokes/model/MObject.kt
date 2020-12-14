package com.example.chuknorrisjokes.model

import com.google.gson.annotations.SerializedName

data class MObject(
   @SerializedName("value")
   var jokeList: ArrayList<Joke>? = null
)