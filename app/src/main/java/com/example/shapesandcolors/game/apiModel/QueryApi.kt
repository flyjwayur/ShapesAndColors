package com.example.shapesandcolors.game.apiModel

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QueryApi {
    @GET("id")
    fun getQuery(
        @Query("format") format: String = "json",
        @Query("hex") hex: String
    ): Call<QueryResult>
}