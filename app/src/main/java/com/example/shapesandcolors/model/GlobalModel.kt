package com.example.shapesandcolors.model

object GlobalModel {

    val colors: MutableList<ColorData> = java.util.ArrayList()
    data class color(val name:String)

    init{
        colors.add(ColorData("black", "#000000"))
        colors.add(ColorData ("green", "#008000"))
    }
}