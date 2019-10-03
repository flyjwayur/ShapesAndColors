package com.example.shapesandcolors.model

import com.google.gson.annotations.SerializedName

data class ColorInfo(
    @SerializedName("colorInfo")
    val colors:String
)

data class Query(
    @SerializedName("searchInfo")
    val colorInfo:ColorInfo
)

data class QueryResult(
    @SerializedName("query")
    val query:Query
)