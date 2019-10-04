package com.example.shapesandcolors

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.shapesandcolors.model.*
import kotlinx.android.synthetic.main.activity_fetch_image.*
import retrofit2.Call
import retrofit2.Response
import java.io.BufferedInputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class FetchImageActivity : AppCompatActivity() {

    private val queryService = QueryApiService()
    //Image URL from Color API to fetch color square
    data class URLparams(val url: URL)
    private var imageLink:String? = null

    inner class GetConn : AsyncTask<Unit?, Unit, Bitmap>(){

        override fun doInBackground(vararg urlp: Unit?): Bitmap?{
            var img: Bitmap? = null
            try {
                val url = URL(imageLink)
                val conn = url.openConnection() as HttpURLConnection
                val istream: InputStream = conn.getInputStream()
                val bis = BufferedInputStream(istream)
                img = BitmapFactory.decodeStream(bis)
                bis.close()
                istream.close()

            }catch (e: Exception) {
                Log.e("Connection", "Reading error", e);
            }

            return img
        }

        override fun onPostExecute(result: Bitmap?) {
            imgV_APIshape.setImageBitmap(result)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_image)

        val adapter = ColorListAdapter(this, GlobalModel.colors)
        listView_colors.adapter = adapter

        listView_colors.setOnItemClickListener { _, _, position, _ ->
            if (isNetworkAvailable()) {
                Log.d("after check Network", "$imageLink")
                // Fetch the json data
                //Fetch the image data
//            val myURLparams = URLparams(URL(imageLink))
                fetchColorFromAPI(GlobalModel.colors[position].hex)

                if(imageLink != null){
                    GetConn().execute()
                }
            }
        }

        listView_colors.setOnItemLongClickListener { _, _, position, _ ->
            val selectedColor = GlobalModel.colors[position]
            val detailIntent = FetchImageDetailActivity.newIntent(this, selectedColor)
            startActivity(detailIntent)
            true
        }


    }

    private fun fetchColorFromAPI(hex: String) {
        val call: Call<QueryResult> = queryService.getQuery(hex)
        call.enqueue(object : retrofit2.Callback<QueryResult> {
            override fun onFailure(call: Call<QueryResult>, t: Throwable) {
                println(t.toString())
            }

            override fun onResponse(call: Call<QueryResult>, response: Response<QueryResult>) {
                if (response.body() != null)
                    imageLink =  response.body()?.image?.named
                    colorFromAPI.text = imageLink
            }
        })
    }


    private fun isNetworkAvailable(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnected == true
    }

    private inner class ColorListAdapter(
        context: Context,
        private val colors: MutableList<ColorData>
    ) : BaseAdapter() {

        private val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return colors.size
        }

        override fun getItem(position: Int): Any {
            return colors[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val rowView = inflater.inflate(R.layout.item_color, parent, false)

            val thisColorData = colors[position]
            var tv = rowView.findViewById(R.id.tvName) as TextView
            tv.text = thisColorData.name

            tv = rowView.findViewById(R.id.tvHex) as TextView
            tv.text = thisColorData.hex

            return rowView
        }
    }
}
