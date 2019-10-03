package com.example.shapesandcolors

import android.content.Context
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

const val tag = "DGB"
const val resp = "RES"
class FetchImageActivity : AppCompatActivity() {

    private val queryService = QueryApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_image)

        val adapter = ColorListAdapter(this, GlobalModel.colors)
        listView_colors.adapter = adapter

        listView_colors.setOnItemClickListener { _, _, position, _ ->
            Log.d(tag, "Selected $position")
            fetchColorFromAPI(GlobalModel.colors[position].hex)
            Log.d(resp, GlobalModel.colors[position].hex)
        }

        listView_colors.setOnItemLongClickListener{ _, _, position, _ ->
            val selectedColor = GlobalModel.colors[position]
            val detailIntent = FetchImageDetailActivity.newIntent(this, selectedColor)
            startActivity(detailIntent)
            true
        }
    }

    private fun fetchColorFromAPI(hex: String){
        val call: Call<QueryResult> = queryService.getQuery(hex)
        call.enqueue(object : retrofit2.Callback<QueryResult>{
            override fun onFailure(call: Call<QueryResult>, t: Throwable) {
                println(t.toString())
            }

            override fun onResponse(call: Call<QueryResult>, response: Response<QueryResult>) {
                if(response.body() != null){
                     Log.d(resp, "${response.body()}")
                     colorFromAPI.text = response.body()?.query?.colorInfo?.colors.toString()
                }
            }
        })
    }

    private inner class ColorListAdapter(context: Context, private val colors: MutableList<ColorData>) : BaseAdapter(){

        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
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
