package com.example.shapesandcolors

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shapesandcolors.game.apiModel.ColorData
import com.example.shapesandcolors.game.apiModel.GlobalModel
import kotlinx.android.synthetic.main.grid_view_color_item.view.*


const val tag = "DBG"

class ColorsAdapter(
    val colorList: MutableList<ColorData>,
    val context: Context,
    val adapterOnClick: (String) -> Unit
) : RecyclerView.Adapter<ColorsAdapter.ColorsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColorsViewHolder {
        val v = LayoutInflater.from(parent?.context)
            .inflate(R.layout.grid_view_color_item, parent, false)
        return ColorsViewHolder(v)
    }

    override fun getItemCount(): Int {
        Log.d(tag, colorList.count().toString())
        return colorList.size
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val colorName = colorList[position].name
        val colorHex = colorList[position].hex
        Log.d(tag, "color name, hex: $colorName, $colorHex")
        if (colorHex === "#000000" || colorHex === "#0000FF") {
            holder.containerView.textV_oneColor.setTextColor(Color.parseColor("#FFFFFF"))
        }
        holder.containerView.textV_oneColor.text = colorName

        holder.containerView.textV_oneColor.setBackgroundColor(Color.parseColor(colorHex))
        holder.itemView.setOnClickListener {
            Log.d(tag, "click item")
            adapterOnClick(GlobalModel.colors[position].hex)
        }
    }

    inner class ColorsViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView)
}