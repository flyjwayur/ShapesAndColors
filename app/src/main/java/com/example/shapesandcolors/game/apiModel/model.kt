package com.example.shapesandcolors.game.apiModel

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("named")
    val named: String
)

data class QueryResult(
    @SerializedName("image")
    val image: Image
)
