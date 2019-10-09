package com.example.shapesandcolors.game.gameModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.shapesandcolors.R
import com.example.shapesandcolors.game.apiModel.ColorImageData

class GameViewModel: ViewModel(){

    //Image URL from Color API to fetch color square
    var imageLink: String? = null
    var selectedHex: String? = null
    val colorImageObjects: HashMap<String, ColorImageData> = HashMap()
    lateinit var keyOfselectedColorObject:String
    lateinit var colorOfselectedColorObject:String
    var srcOfselectedColorObject:Int? = null

    init {
        Log.i("GameViewModel", "GameViewModel created")
        //Add values to the colorImageObjects hashmap
        addValuesToColorImageObjects()

        //Shuffle images for a color game
        shuffleImage()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

    private fun addValuesToColorImageObjects() {
        colorImageObjects["Apple"] = ColorImageData(
            "red",
            R.drawable.apple
        )
        colorImageObjects["Fish"] = ColorImageData(
            "blue",
            R.drawable.fish
        )
        colorImageObjects["Sun"] = ColorImageData(
            "yellow",
            R.drawable.sun
        )
        colorImageObjects["Orange"] = ColorImageData(
            "orange",
            R.drawable.oranges
        )
        colorImageObjects["Watermelon"] = ColorImageData(
            "green",
            R.drawable.watermelon
        )
        colorImageObjects["Eggplant"] = ColorImageData(
            "purple",
            R.drawable.eggplant
        )
        colorImageObjects["Tire"] = ColorImageData(
            "black",
            R.drawable.tire
        )
        colorImageObjects["Snowman"] = ColorImageData(
            "white",
            R.drawable.snowman
        )
    }

    fun shuffleImage():Int?{
        keyOfselectedColorObject = colorImageObjects.keys.random()
        val valueOfSelectedColorObject = colorImageObjects[keyOfselectedColorObject]
        colorOfselectedColorObject = valueOfSelectedColorObject!!.color
        srcOfselectedColorObject = valueOfSelectedColorObject!!.imgSrc
        return srcOfselectedColorObject
    }

}