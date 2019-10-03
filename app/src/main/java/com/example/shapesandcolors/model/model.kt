package com.example.shapesandcolors.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("named")
    val named: String)

data class Query(
    @SerializedName("image")
    val image: Image)

data class QueryResult(
    @SerializedName("query")
    val query: Query)