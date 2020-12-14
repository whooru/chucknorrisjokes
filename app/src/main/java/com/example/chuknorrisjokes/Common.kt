package com.example.chuknorrisjokes

class Common {
    private val BASE_URL = "http://api.icndb.com/"
    val retrofitServices: JokeService
        get() = RetrofitClient().getClient(BASE_URL).create(JokeService::class.java)
}