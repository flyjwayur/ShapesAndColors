package com.example.shapesandcolors.game.apiModel

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QueryApiService {
    private val BASE_URL = "http://www.thecolorapi.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QueryApi::class.java)

    fun getQuery(hex: String): Call<QueryResult> {
        return api.getQuery(hex = hex)
    }

}