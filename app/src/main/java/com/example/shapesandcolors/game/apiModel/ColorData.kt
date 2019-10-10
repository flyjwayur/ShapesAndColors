package com.example.shapesandcolors.game.apiModel

class ColorData(var name: String, var hex: String) {
    override fun toString(): String {
        return "$name $hex"
    }
}