package com.example.shapesandcolors


import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shapesandcolors.model.*
import kotlinx.android.synthetic.main.activity_fetch_image.*
import retrofit2.Call
import retrofit2.Response
import kotlin.collections.HashMap


class FetchImageActivity : AppCompatActivity() {

    private val queryService = QueryApiService()
    //Image URL from Color API to fetch color square
    private var imageLink: String? = null
    private var selectedHex: String? = null
    val colorImageObjects: HashMap<String, ColorImageData> = HashMap()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_fetch_image)

        //Display a color palette with a recycler + grid view
        recyclerV_colors_grid.layoutManager = GridLayoutManager(this, 3)
        recyclerV_colors_grid.adapter =
            ColorsAdapter(GlobalModel.colors, this, { hexItem -> fetchColorFromAPI(hexItem) })

        //Add values to the colorImageObjects hashmap
        addValuesToColorImageObjects()

        val keyOfselectedColorObject = colorImageObjects.keys.random()
        val valueOfSelectedColorObject = colorImageObjects[keyOfselectedColorObject]
        val colorOfselectedColorObject = valueOfSelectedColorObject!!.color
        val srcOfselectedColorObject = valueOfSelectedColorObject!!.imgSrc

        imgV_colorObject.setImageResource(srcOfselectedColorObject)
        textV_colorGameDesc2.text =
            "What is a color of $keyOfselectedColorObject ? "

        button_check.setOnClickListener { it ->
            var nameOfSelectedHex: String

            if (selectedHex !== null) {
                nameOfSelectedHex =
                    GlobalModel.colors.filter { it -> it.hex === selectedHex }.first().name
                Log.d("TEST", nameOfSelectedHex)
                if (colorOfselectedColorObject === nameOfSelectedHex) {
                    Log.d("TEST -true", "TRUE")
                } else {
                    Log.d("TEST - false", "FALSE")
                }
            } else {
                val toast =
                    Toast.makeText(this, "Choose a color from color palette", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun addValuesToColorImageObjects() {
        colorImageObjects["Apple"] = ColorImageData("red", R.drawable.apple)
        colorImageObjects["Fish"] = ColorImageData("blue", R.drawable.fish)
        colorImageObjects["Sun"] = ColorImageData("yellow", R.drawable.sun)
        colorImageObjects["Orange"] = ColorImageData("orange", R.drawable.oranges)
        colorImageObjects["Watermelon"] = ColorImageData("green", R.drawable.watermelon)
        colorImageObjects["Eggplant"] = ColorImageData("purple", R.drawable.eggplant)
        colorImageObjects["Tire"] = ColorImageData("black", R.drawable.tire)
        colorImageObjects["Snowman"] = ColorImageData("white", R.drawable.snowman)
    }

    private fun fetchColorFromAPI(hex: String) {
        selectedHex = hex
        val call: Call<QueryResult> = queryService.getQuery(hex)
        call.enqueue(object : retrofit2.Callback<QueryResult> {
            override fun onFailure(call: Call<QueryResult>, t: Throwable) {
                println(t.toString())
            }

            override fun onResponse(call: Call<QueryResult>, response: Response<QueryResult>) {
                if (response.body() != null)
                    imageLink = response.body()?.image?.named
                webV_APIshape.loadUrl(imageLink)
            }
        })
    }
}
