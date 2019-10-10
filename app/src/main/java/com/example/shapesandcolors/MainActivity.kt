package com.example.shapesandcolors

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shapesandcolors.game.FetchImageActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var actList = arrayOf("Ar", "FetchImage", "Play")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // a circular animation for launching different activities
        circle_menu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.play, R.drawable.stop)
            .addSubMenu(Color.parseColor("#25BCFE"), R.drawable.icon_stop)
            .addSubMenu(Color.parseColor("#6D4C41"), R.drawable.sound)
            .addSubMenu(Color.parseColor("#1a237e"), R.drawable.sound2)
            .setOnMenuSelectedListener {
                when(actList[it]) {
                    "Ar" -> {
                        val intent = Intent(this, ArActivity :: class.java)
                        startActivity(intent)
                    }
                    "FetchImage" -> {
                        val intent = Intent(this, FetchImageActivity:: class.java)
                        startActivity(intent)
                    }
                    "Play" -> {
                        val intent = Intent(this, PlayActivity :: class.java)
                        startActivity(intent)
                    }
                }
            }

    }
}
