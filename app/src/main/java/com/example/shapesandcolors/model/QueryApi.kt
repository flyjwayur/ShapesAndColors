package com.example.shapesandcolors.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QueryApi {
    @GET("id?")
    fun getQuery(@Query("hex") hex: String="hex",
                 @Query("format") format: String="json"): Call<QueryResult>
}