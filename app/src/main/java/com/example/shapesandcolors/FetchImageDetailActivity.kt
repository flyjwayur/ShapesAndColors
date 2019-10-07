package com.example.shapesandcolors

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shapesandcolors.model.ColorData
import kotlinx.android.synthetic.main.activity_fetch_image_detail.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FetchImageDetailActivity : AppCompatActivity() {

    companion object {
        //Still need to figure out more with extra title/url
        const val EXTRA_TITLE = "hex=00000"
        const val EXTRA_URL = "id?format=svg&"
        const val COLORAPI_URL = "http://www.thecolorapi.com/"

        fun newIntent(context: Context, colorData: ColorData): Intent {
            val detailIntent = Intent(context, FetchImageDetailActivity::class.java)

            detailIntent.putExtra(EXTRA_TITLE, colorData.name)
            detailIntent.putExtra(EXTRA_TITLE, colorData.hex)
            detailIntent.putExtra(EXTRA_URL, COLORAPI_URL)

            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_image_detail)

        val title = intent.extras?.getString(EXTRA_TITLE)
        val url = intent.extras?.getString(EXTRA_URL)

        detail_web_view.loadUrl("$url$title")
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(COLORAPI_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
