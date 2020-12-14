package com.example.chuknorrisjokes

import com.example.chuknorrisjokes.model.MObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JokeService {
    @GET("jokes/random/{number}")
    fun getJokes(@Path("number") number: String): Call<MObject>

}

