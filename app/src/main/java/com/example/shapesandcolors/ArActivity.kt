package com.example.shapesandcolors

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.android.synthetic.main.activity_ar.*

class ArActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var arrayView : Array<View>
    internal var selected = 1 // chosen renderable by default
    private lateinit var fragment: ArFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        fragment = supportFragmentManager.findFragmentById(R.id.arimage_fragment) as ArFragment
        arrayView = arrayOf(
            imv_circle, imv_heart, imv_hexagon, imv_circle2, imv_heart2,
            imv_hexagon2, imv_circle3, imv_heart3, imv_hexagon3, imv_circle4)

        setUpClickListener()
    }

    private fun setUpClickListener() {
        for (i in arrayView.indices) {
            arrayView[i].setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.imv_circle -> {
                selected = 1
                myBackground(view.id)
                // Toast.makeText(this, "Image $selected", Toast.LENGTH_SHORT).show()
            }
            R.id.imv_heart -> {
                selected = 2
                myBackground(view.id)
            }
            R.id.imv_hexagon -> {
                selected = 3
                myBackground(view.id)
            }
            R.id.imv_circle2 -> {
                selected = 4
                myBackground(view.id)
            }
            R.id.imv_heart2 -> {
                selected = 5
                myBackground(view.id)
            }
            R.id.imv_hexagon2 -> {
                selected = 6
                myBackground(view.id)
            }
            R.id.imv_circle3 -> {
                selected = 7
                myBackground(view.id)
            }
            R.id.imv_heart3 -> {
                selected = 8
                myBackground(view.id)
            }
            R.id.imv_hexagon3 -> {
                selected = 9
                myBackground(view.id)
            }
            R.id.imv_circle4 -> {
                selected = 10
                myBackground(view.id)
            }
        }
    }

    private fun myBackground(myid: Int) {
        for (i in arrayView.indices) {
            if (arrayView[i].id == myid) {
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639"))
            } else {
                arrayView[i].setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }
}
