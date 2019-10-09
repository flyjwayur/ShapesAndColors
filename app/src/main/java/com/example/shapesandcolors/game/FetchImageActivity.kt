package com.example.shapesandcolors.game


import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shapesandcolors.ColorsAdapter
import com.example.shapesandcolors.R
import com.example.shapesandcolors.game.apiModel.ColorImageData
import com.example.shapesandcolors.game.apiModel.GlobalModel
import com.example.shapesandcolors.game.apiModel.QueryApiService
import com.example.shapesandcolors.game.apiModel.QueryResult
import com.example.shapesandcolors.game.gameModel.GameViewModel
import kotlinx.android.synthetic.main.activity_fetch_image.*
import retrofit2.Call
import retrofit2.Response
import kotlin.collections.HashMap


class FetchImageActivity : AppCompatActivity() {

    private val queryService = QueryApiService()

    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_fetch_image)

        //Display a color palette with a recycler + grid view
        recyclerV_colors_grid.layoutManager = GridLayoutManager(this, 3)
        recyclerV_colors_grid.adapter =
            ColorsAdapter(
                GlobalModel.colors,
                this,
                { hexItem -> fetchColorFromAPI(hexItem) })


        Log.i("GameActivity", "Called ViewModelProviders.of")
        // Life Cycle will create game viewModel instance for us
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        //Set the image to image view and name of the color object to game description
        imgV_colorObject.setImageResource(viewModel.shuffleImage()!!)
        textV_colorGameDesc2.text =
            "What is a color of ${viewModel.keyOfselectedColorObject} ? "

        textV_score.text = "Score: ${viewModel.score}"
        button_check.setOnClickListener { it ->
            var nameOfSelectedHex: String

            if (viewModel.selectedHex !== null) {
                nameOfSelectedHex =
                    GlobalModel.colors.filter { it -> it.hex === viewModel.selectedHex }.first()
                        .name

                if (viewModel.colorOfselectedColorObject === nameOfSelectedHex) {
                    viewModel.AddScore()
                    updateScoreText()
                    var dialog = ColorGameDialogFrag()
                    dialog.show(supportFragmentManager, "dialog")
                } else {
                    //Provide a game guide message for encouragement
                    val gameMessage = viewModel.gameGuideMessages.random()
                    textV_colorGameDesc2.text =
                        "${gameMessage}"
                }
            } else {
                val toast =
                    Toast.makeText(this, "Choose a color from color palette", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun fetchColorFromAPI(hex: String) {
        viewModel.selectedHex = hex
        val call: Call<QueryResult> = queryService.getQuery(hex)
        call.enqueue(object : retrofit2.Callback<QueryResult> {
            override fun onFailure(call: Call<QueryResult>, t: Throwable) {
                println(t.toString())
            }

            override fun onResponse(call: Call<QueryResult>, response: Response<QueryResult>) {
                if (response.body() != null)
                    viewModel.imageLink = response.body()?.image?.named
                webV_APIshape.loadUrl(viewModel.imageLink)
            }
        })
    }

    fun updateImage() {
        imgV_colorObject.setImageResource(viewModel.shuffleImage()!!)

    }

    fun updateDescText() {
        textV_colorGameDesc2.text =
            "What is a color of ${viewModel.keyOfselectedColorObject} ? "
    }

    fun updateScoreText(){
        textV_score.text = "Score: ${viewModel.score}"
    }
}
