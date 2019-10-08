package com.example.shapesandcolors.model

class ColorImageData(var color:String, var imgSrc:Int) {
    override fun toString():String {
        return "$color $imgSrc"
    }

    companion object {
        fun getColor(colorImageData: ColorImageData) : String{
            return colorImageData.color;
        }

        fun getImgSrc(colorImageData: ColorImageData):Int {
            return colorImageData.imgSrc
        }
    }
}
