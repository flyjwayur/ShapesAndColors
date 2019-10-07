package com.example.shapesandcolors.model

object GlobalModel {

    val colors: MutableList<ColorData> = java.util.ArrayList()

    data class color(val name: String)

    init {
        colors.add(ColorData("red", "#FF0000"))
        colors.add(ColorData("blue", "#0000FF"))
        colors.add(ColorData("yellow", "#FFFF00"))
        colors.add(ColorData("orange", "#FFA500"))
        colors.add(ColorData("green", "#00FF00"))
        colors.add(ColorData("purple", "#AD26D8"))
        colors.add(ColorData("black", "#000000"))
        colors.add(ColorData("white", "#FFFFFF"))
    }
}