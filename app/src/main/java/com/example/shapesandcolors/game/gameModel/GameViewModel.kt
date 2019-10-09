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
    val gameGuideMessages:ArrayList<String> = arrayListOf()

    init {
        Log.i("GameViewModel", "GameViewModel created")
        //Add values to the colorImageObjects hashmap
        addValuesToColorImageObjects()
        addGuideMessageToGameDescText()

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

    private fun addGuideMessageToGameDescText(){
        gameGuideMessages.add("Let's try once more")
        gameGuideMessages.add("We can try another color?")
        gameGuideMessages.add("How about choosing another color?")
        gameGuideMessages.add("You're almost there")
    }

    fun shuffleImage():Int?{
        keyOfselectedColorObject = colorImageObjects.keys.random()
        val valueOfSelectedColorObject = colorImageObjects[keyOfselectedColorObject]
        colorOfselectedColorObject = valueOfSelectedColorObject!!.color
        srcOfselectedColorObject = valueOfSelectedColorObject!!.imgSrc
        return srcOfselectedColorObject
    }

}