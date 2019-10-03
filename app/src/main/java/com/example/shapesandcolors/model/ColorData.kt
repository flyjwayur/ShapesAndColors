package com.example.shapesandcolors.model

class ColorData(var name: String, var hex: String){
    override fun toString():String{
        return "$name $hex"
    }
}